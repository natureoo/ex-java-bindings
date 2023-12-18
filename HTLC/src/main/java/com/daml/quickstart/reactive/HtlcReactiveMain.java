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
    public static final String BOND_ISSUER_USER = "BondIssuer::1220741beaf119ba1e725820a643535e60c2cb4c7f81f4ecb5f8888d582ae4d3fffd";

    public static final String HOST = "127.0.0.1";

    public static final int PORT = 6865;


    public static void main(String[] args) {
        // Extract host and port from arguments
        String host = HOST;
        int port = PORT;

        // each party will create this number of initial Ping contracts

        // create a client object to access services on the ledger
        DamlLedgerClient client = DamlLedgerClient.newBuilder(host, port).build();

        // Connects to the ledger and runs initial validation
        client.connect();


        Id id = new Id("BOND");
        String version = "v0.1";


        InstrumentKey  instrumentKey = new InstrumentKey(ALICE_USER, BOND_ISSUER_USER, id, version);
        AccountKey ownerAccountKey = new AccountKey( BANK_USER,ALICE_USER, id);

        AccountKey receiverAccountKey = new AccountKey( BANK_USER,BOB_USER, id);

        List<String> aliceBankList = Arrays.asList(ALICE_USER, BANK_USER);
        List<String> bobBankList = Arrays.asList(BOB_USER, BANK_USER);
        List<String> bankList = Arrays.asList( BANK_USER);
        List<String> bondIssuerList = Arrays.asList(BOND_ISSUER_USER);
        List<String> bondIssuerBankList = Arrays.asList(BOND_ISSUER_USER, BANK_USER);
        List<String> allPartiesList = Arrays.asList(ALICE_USER, BOB_USER, BANK_USER, BOND_ISSUER_USER);
//        Set<String> allParties =
//        Map.fromList [("HTLC", allParties)]

         Map<String, Unit> allPartiesMap = new HashMap<>();
        allPartiesMap.put(ALICE_USER, Unit.getInstance());
        allPartiesMap.put(BOB_USER, Unit.getInstance());
        allPartiesMap.put(BANK_USER, Unit.getInstance());
        allPartiesMap.put(BOND_ISSUER_USER, Unit.getInstance());
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
        events = submitCommand( client, aliceBankList, allPartiesList,  Fungible.TEMPLATE_ID, command);
//        String tx_id = transaction.getTransactionId();
        createdEvent = (CreatedEvent)events.get(0);
        Fungible.Contract fungibleContract = Fungible.Contract.fromCreatedEvent(createdEvent);
        Transferable.ContractId transferableContractId = fungibleContract.id.toInterface(Transferable.INTERFACE);
        logger.info("transferableContractId[{}]", transferableContractId);

        String hash = "HASH";
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
//        Date tomorrow = today;
        Instant unlockTime = tomorrow.toInstant();
        HTLCProposal htlcProposal = new HTLCProposal(ALICE_USER, BOB_USER, transferableContractId, hash, unlockTime);
        command = htlcProposal.create().commands().get(0);
        List<String> aliceList = new ArrayList<>();
        aliceList.add(ALICE_USER);
        List<String> bobList = new ArrayList<>();
        bobList.add(BOB_USER);
        events = submitCommand(client, aliceList, bobList, HTLCProposal.TEMPLATE_ID, command);
        createdEvent = (CreatedEvent)events.get(0);
        HTLCProposal.Contract htlcProposalContract = HTLCProposal.Contract.fromCreatedEvent(createdEvent);



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

//        try {
//            // wait a couple of seconds for the processing to finish
//            Thread.sleep(20000);
//            System.exit(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

//    public  fetch(Identifier templateId, String contractId, Class<T> contractType) {
//        // TODO very inefficient; fix this
//        return queryAllRaw(templateId)
//                .filter(e -> contractId.equals(e.getContractId()))
//                .map(e -> parseAs(e, contractType))
//                .map(Optional::of)
//                .first(Optional.empty())
//                .blockingGet();
//    }
//
//    private Flowable<CreatedEvent> queryAllRaw(Identifier templateId) {
//        CompletableFuture<LedgerOffset> finalOffset = new CompletableFuture<>();
//
//        FiltersByParty filter = getFiltersByParty(templateId);
//
//        return getClient().getActiveContractSetClient()
//                .getActiveContracts(filter, false)
//                .doOnEach(notification -> {
//                    Optional.ofNullable(notification.getValue())
//                            .flatMap(GetActiveContractsResponse::getOffset)
//                            .ifPresent(s -> finalOffset.complete(new LedgerOffset.Absolute(s)));
//                })
//                .flatMap(resp -> Flowable.fromIterable(resp.getCreatedEvents()))
//                .filter(e -> compareTemplateId( e.getTemplateId(), templateId));
//    }


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
