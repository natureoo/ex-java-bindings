package com.daml.quickstart.model.daml.finance.account.account;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.account.account.Controllers;
import com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;
import com.daml.quickstart.model.daml.finance.interface$.util.disclosure.Disclosure;

import java.util.*;

public final class Account extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("99a5a2f08e458a0b90713f4d723fccdcc55474824126bea47a635460ec6eb688", "Daml.Finance.Account.Account", "Account");

  public static final Choice<Account, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Account> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.account.account.Account", TEMPLATE_ID,
        ContractId::new, v -> Account.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive));

  public final String custodian;

  public final String owner;

  public final Controllers controllers;

  public final Id id;

  public final String description;

  public final Factory.ContractId holdingFactoryCid;

  public final Map<String, Set<String>> observers;

  public Account(String custodian, String owner, Controllers controllers, Id id, String description,
      Factory.ContractId holdingFactoryCid, Map<String, Set<String>> observers) {
    this.custodian = custodian;
    this.owner = owner;
    this.controllers = controllers;
    this.id = id;
    this.description = description;
    this.holdingFactoryCid = holdingFactoryCid;
    this.observers = observers;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Account>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Account>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Account>>>(new CreateCommand(Account.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Account>>> create(
      String custodian, String owner, Controllers controllers, Id id, String description,
      Factory.ContractId holdingFactoryCid, Map<String, Set<String>> observers) {
    return new Account(custodian, owner, controllers, id, description, holdingFactoryCid,
        observers).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Account> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Account fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Account> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(7);
    fields.add(new DamlRecord.Field("custodian", new Party(this.custodian)));
    fields.add(new DamlRecord.Field("owner", new Party(this.owner)));
    fields.add(new DamlRecord.Field("controllers", this.controllers.toValue()));
    fields.add(new DamlRecord.Field("id", this.id.toValue()));
    fields.add(new DamlRecord.Field("description", new Text(this.description)));
    fields.add(new DamlRecord.Field("holdingFactoryCid", this.holdingFactoryCid.toValue()));
    fields.add(new DamlRecord.Field("observers", this.observers.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Account> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(7, recordValue$);
      String custodian = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String owner = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Controllers controllers = Controllers.valueDecoder().decode(fields$.get(2).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(3).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(4).getValue());
      Factory.ContractId holdingFactoryCid =
          new Factory.ContractId(fields$.get(5).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(6).getValue());
      return new Account(custodian, owner, controllers, id, description, holdingFactoryCid,
          observers);
    } ;
  }

  public static ContractFilter<Contract> contractFilter() {
    return ContractFilter.of(COMPANION);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Account)) {
      return false;
    }
    Account other = (Account) object;
    return Objects.equals(this.custodian, other.custodian) &&
        Objects.equals(this.owner, other.owner) &&
        Objects.equals(this.controllers, other.controllers) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.holdingFactoryCid, other.holdingFactoryCid) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.custodian, this.owner, this.controllers, this.id, this.description,
        this.holdingFactoryCid, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.account.account.Account(%s, %s, %s, %s, %s, %s, %s)",
        this.custodian, this.owner, this.controllers, this.id, this.description,
        this.holdingFactoryCid, this.observers);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Account> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Account, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public Disclosure.ContractId toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.ContractId(this.contractId);
    }

    public com.daml.quickstart.model.daml.finance.interface$.account.account.Account.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.account.account.Account.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.account.account.Account.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(Disclosure.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.account.account.Account.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Account> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Account> {
    public Contract(ContractId id, Account data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Account> getCompanion() {
      return COMPANION;
    }

    public static Contract fromIdAndRecord(String contractId, DamlRecord record$,
        Optional<String> agreementText, java.util.Set<String> signatories,
        java.util.Set<String> observers) {
      return COMPANION.fromIdAndRecord(contractId, record$, agreementText, signatories, observers);
    }

    public static Contract fromCreatedEvent(CreatedEvent event) {
      return COMPANION.fromCreatedEvent(event);
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd implements Exercises<CreateAndExerciseCommand> {
    CreateAnd(Template createArguments) {
      super(createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Account, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public Disclosure.CreateAnd toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.CreateAnd(COMPANION, this.createArguments);
    }

    public com.daml.quickstart.model.daml.finance.interface$.account.account.Account.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.account.account.Account.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.account.account.Account.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
