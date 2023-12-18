// Copyright (c) 2023 Digital Asset (Switzerland) GmbH and/or its affiliates. All rights reserved.
// SPDX-License-Identifier: Apache-2.0

package com.daml.quickstart.reactive;

import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.rxjava.DamlLedgerClient;
import com.daml.ledger.rxjava.LedgerClient;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.account.account.Factory;
import com.daml.quickstart.model.daml.finance.holding.fungible.Fungible;
import com.daml.quickstart.model.daml.finance.interface$.account.account.Controllers;
import com.daml.quickstart.model.daml.finance.interface$.holding.transferable.Transferable;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.synfini.htlc.HTLC;
import com.daml.quickstart.model.synfini.htlc.HTLCProposal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.*;


public class HtlcReactiveMain {

    private final static Logger logger = LoggerFactory.getLogger(HtlcReactiveMain.class);

    // application id used for sending commands
    public static final String APP_ID = "HTLCReactiveApp";

    // constants for referring to users with access to the parties
    public static final String ALICE_USER = "Alice::1220741beaf119ba1e725820a643535e60c2cb4c7f81f4ecb5f8888d582ae4d3fffd";
    public static final String BOB_USER = "Bob::1220741beaf119ba1e725820a643535e60c2cb4c7f81f4ecb5f8888d582ae4d3fffd";

    public static final String BANK_USER = "Bank::1220741beaf119ba1e725820a643535e60c2cb4c7f81f4ecb5f8888d582ae4d3fffd";
    public static final String BOND_ISSUER_USER = "Government::1220741beaf119ba1e725820a643535e60c2cb4c7f81f4ecb5f8888d582ae4d3fffd";

    public static final String HOST = "127.0.0.1";

    public static final int PORT = 6865;

    private static DamlLedgerClient client;

    private static Transferable.ContractId transferableContractId;

    private static Command command;

    private static CreatedEvent createdEvent;

    private static List<Event> events;

    private static HTLCProposal.Contract htlcProposalContract;

    private static AccountKey ownerAccountKey;

    private static AccountKey receiverAccountKey;

    private static List<String> aliceList = Arrays.asList(ALICE_USER);
    private static List<String> aliceBankList = Arrays.asList(ALICE_USER, BANK_USER);
    private static List<String> bankGovList = Arrays.asList( BANK_USER, BOND_ISSUER_USER);
    private static List<String> aliceBankGovList = Arrays.asList(ALICE_USER, BANK_USER, BOND_ISSUER_USER);
    private static List<String> bobList = Arrays.asList(BOB_USER);
    private static List<String> bobBankList = Arrays.asList(BOB_USER, BANK_USER);
    private static List<String> bankList = Arrays.asList( BANK_USER);
    private static List<String> bondIssuerList = Arrays.asList(BOND_ISSUER_USER);
    private static List<String> bondIssuerBankList = Arrays.asList(BOND_ISSUER_USER, BANK_USER);
    private static List<String> allPartiesList = Arrays.asList(ALICE_USER, BOB_USER, BANK_USER, BOND_ISSUER_USER);

    private static void prepare(){
        // Extract host and port from arguments
        String host = HOST;
        int port = PORT;

        // each party will create this number of initial Ping contracts

        // create a client object to access services on the ledger
        client = DamlLedgerClient.newBuilder(host, port).build();

        // Connects to the ledger and runs initial validation
        client.connect();


        Id id = new Id("BOND");
        String version = "v0.1";


        InstrumentKey  instrumentKey = new InstrumentKey(BANK_USER, BOND_ISSUER_USER, id, version);
        ownerAccountKey = new AccountKey( BANK_USER,ALICE_USER, id);

        receiverAccountKey = new AccountKey( BANK_USER,BOB_USER, id);


//        Set<String> allParties =
//        Map.fromList [("HTLC", allParties)]

        Map<String, Unit> allPartiesMap = new HashMap<>();
        allPartiesMap.put(ALICE_USER, Unit.getInstance());
        allPartiesMap.put(BOB_USER, Unit.getInstance());
        allPartiesMap.put(BANK_USER, Unit.getInstance());
//        allPartiesMap.put(BOND_ISSUER_USER, Unit.getInstance());
        Set<String> allPartieesSet = new Set<>(allPartiesMap);
        Map<String, Set<String>> observers = new HashMap<>();
        observers.put("HTLC", allPartieesSet);

        Factory accountFactory = new Factory(BANK_USER, observers);
        Command command = accountFactory.create().commands().get(0);
        List<Event> events = submitCommand(client, bankList, bankList, Factory.TEMPLATE_ID, command);
        CreatedEvent createdEvent = (CreatedEvent)events.get(0);
        Factory.Contract  accountFactoryContract = Factory.Contract.fromCreatedEvent(createdEvent);
        com.daml.quickstart.model.daml.finance.interface$.account.factory.Factory.ContractId acountFactorInterfaceContractId = accountFactoryContract.id.toInterface(com.daml.quickstart.model.daml.finance.interface$.account.factory.Factory.INTERFACE);
        Set<String> emptySet = new Set<>(new HashMap<>());
        Controllers controllers = new Controllers(emptySet, emptySet);
//        Account account = new Account(BANK_USER, ALICE_USER, controllers)

        com.daml.quickstart.model.daml.finance.holding.fungible.Factory FungibleFactory = new com.daml.quickstart.model.daml.finance.holding.fungible.Factory(BOND_ISSUER_USER, observers);
        command = FungibleFactory.create().commands().get(0);
        events = submitCommand(client, bondIssuerBankList, bondIssuerBankList, com.daml.quickstart.model.daml.finance.holding.fungible.Factory.TEMPLATE_ID, command);
        createdEvent = (CreatedEvent)events.get(0);
        com.daml.quickstart.model.daml.finance.holding.fungible.Factory.Contract fungibleFactoryContract = com.daml.quickstart.model.daml.finance.holding.fungible.Factory.Contract.fromCreatedEvent(createdEvent);
        com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId holdingFactoryContractId = fungibleFactoryContract.id.toInterface(com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.INTERFACE);

        try {
            command = acountFactorInterfaceContractId.exerciseCreate(ownerAccountKey, holdingFactoryContractId, controllers, "Alice's account", observers).commands().get(0);
            events = submitCommand(client, aliceBankList, aliceBankList, com.daml.quickstart.model.daml.finance.account.account.Account.TEMPLATE_ID, command);
            createdEvent = (CreatedEvent) events.get(0);
            logger.info("Create Account[{}]", createdEvent);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            command = acountFactorInterfaceContractId.exerciseCreate(receiverAccountKey, holdingFactoryContractId, controllers, "Bob's account", observers).commands().get(0);
            events = submitCommand(client, bobBankList, bobBankList, com.daml.quickstart.model.daml.finance.account.account.Account.TEMPLATE_ID, command);
            createdEvent = (CreatedEvent) events.get(0);
            logger.info("Create Account[{}]", createdEvent);
        }catch (Exception e){
            e.printStackTrace();
        }

        Fungible fungible = new Fungible(instrumentKey, ownerAccountKey, BigDecimal.ONE, Optional.empty(), observers);
        command = fungible.create().commands().get(0);
        events = submitCommand( client, aliceBankGovList, aliceBankGovList,  Fungible.TEMPLATE_ID, command);
//        String tx_id = transaction.getTransactionId();
        createdEvent = (CreatedEvent)events.get(0);
        Fungible.Contract fungibleContract = Fungible.Contract.fromCreatedEvent(createdEvent);
        transferableContractId = fungibleContract.id.toInterface(Transferable.INTERFACE);
        logger.info("transferableContractId[{}]", transferableContractId);
    }

    private static void createHTLCProposal(String hash){
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
//        Date tomorrow = today;
        Instant unlockTime = tomorrow.toInstant();
        HTLCProposal htlcProposal = new HTLCProposal(ALICE_USER, BOB_USER, transferableContractId, hash, unlockTime);
        command = htlcProposal.create().commands().get(0);


        events = submitCommand(client, aliceList, bobList, HTLCProposal.TEMPLATE_ID, command);
        createdEvent = (CreatedEvent)events.get(0);
        htlcProposalContract = HTLCProposal.Contract.fromCreatedEvent(createdEvent);
    }

    private static void acceptHTCLProposal(){

        command = htlcProposalContract.id.exerciseHTLCProposal_Accept(receiverAccountKey).commands().get(0);
        events = submitCommand(client, bobList, aliceList, HTLCProposal.TEMPLATE_ID, command);
//        createdEvent = (CreatedEvent)events.get(0);
        boolean flags = false;
        for(Event event: events){
            if(event.getTemplateId().equals(HTLC.TEMPLATE_ID)) {
                createdEvent = (CreatedEvent) event;
                flags = true;
            }
        }
        if(!flags){
            logger.error("Can not find HTLC contract, return!!!!!");
            return;
        }

    }

    private static void claimBond(String hash){
        HTLC.Contract htlcContract = HTLC.Contract.fromCreatedEvent(createdEvent);

        List<String> aliceBobList = new ArrayList<>();
        aliceBobList.add(ALICE_USER);
        aliceBobList.add(BOB_USER);
        command = htlcContract.id.exerciseHTLC_Claim(hash).commands().get(0);
//        command = htlcContract.id.exerciseHTLC_Unlock().commands().get(0);
        events = submitCommand(client, aliceBobList, bobList, HTLC.TEMPLATE_ID, command);
        for(Event event: events) {
            logger.info("HTLC event[{}]", event);
        }
    }


    public static void main(String[] args) {
        String hash = "HASH";
        prepare();
        createHTLCProposal(hash);
        acceptHTCLProposal();
        claimBond(hash);
    }


    private static List<Event> submitCommand( LedgerClient client, List<String> actAs, List<String> readAs,
                                             Identifier templateIdentifier, Command command) {

            // command that creates the initial Ping contract with the required parameters
            // according to the model


            String commandId = UUID.randomUUID().toString();
         return client.getCommandClient()
                .submitAndWaitForTransaction(
                        "",
                        APP_ID,
                        commandId,
                        actAs,
                        readAs,
                        Collections.singletonList(command)
                )
                .blockingGet().getEvents();
    }
}
