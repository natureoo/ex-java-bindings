package com.daml.quickstart.model.workflow.createaccount;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.daml.finance.interface$.account.factory.Factory;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.*;

public final class Request extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("91deeb52981e7064dbcf05e8dd2a81ef0d8a25fe4aaff70e56ec84fcb11aa28d", "Workflow.CreateAccount", "Request");

  public static final Choice<Request, Accept, AccountKey> CHOICE_Accept = 
      Choice.create("Accept", value$ -> value$.toValue(), value$ -> Accept.valueDecoder()
        .decode(value$), value$ -> AccountKey.valueDecoder().decode(value$));

  public static final Choice<Request, Decline, Unit> CHOICE_Decline = 
      Choice.create("Decline", value$ -> value$.toValue(), value$ -> Decline.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Request, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Request, Withdraw, Unit> CHOICE_Withdraw = 
      Choice.create("Withdraw", value$ -> value$.toValue(), value$ -> Withdraw.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Request> COMPANION = 
      new ContractCompanion.WithoutKey<>("com.daml.quickstart.model.workflow.createaccount.Request",
        TEMPLATE_ID, ContractId::new, v -> Request.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Accept, CHOICE_Decline, CHOICE_Archive, CHOICE_Withdraw));

  public final String custodian;

  public final String owner;

  public Request(String custodian, String owner) {
    this.custodian = custodian;
    this.owner = owner;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Request>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Request>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Request>>>(new CreateCommand(Request.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseAccept} instead
   */
  @Deprecated
  public Update<Exercised<AccountKey>> createAndExerciseAccept(Accept arg) {
    return createAnd().exerciseAccept(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseAccept} instead
   */
  @Deprecated
  public Update<Exercised<AccountKey>> createAndExerciseAccept(String label, String description,
      Factory.ContractId accountFactoryCid,
      com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId holdingFactoryCid,
      List<String> observers) {
    return createAndExerciseAccept(new Accept(label, description, accountFactoryCid,
        holdingFactoryCid, observers));
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseDecline} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseDecline(Decline arg) {
    return createAnd().exerciseDecline(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseDecline} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseDecline() {
    return createAndExerciseDecline(new Decline());
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseWithdraw} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseWithdraw(Withdraw arg) {
    return createAnd().exerciseWithdraw(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseWithdraw} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseWithdraw() {
    return createAndExerciseWithdraw(new Withdraw());
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Request>>> create(
      String custodian, String owner) {
    return new Request(custodian, owner).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Request> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Request fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Request> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(2);
    fields.add(new DamlRecord.Field("custodian", new Party(this.custodian)));
    fields.add(new DamlRecord.Field("owner", new Party(this.owner)));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Request> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2, recordValue$);
      String custodian = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String owner = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      return new Request(custodian, owner);
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
    if (!(object instanceof Request)) {
      return false;
    }
    Request other = (Request) object;
    return Objects.equals(this.custodian, other.custodian) &&
        Objects.equals(this.owner, other.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.custodian, this.owner);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.workflow.createaccount.Request(%s, %s)",
        this.custodian, this.owner);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Request> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Request, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Request> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Request> {
    public Contract(ContractId id, Request data, Optional<String> agreementText,
        Set<String> signatories, Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Request> getCompanion() {
      return COMPANION;
    }

    public static Contract fromIdAndRecord(String contractId, DamlRecord record$,
        Optional<String> agreementText, Set<String> signatories, Set<String> observers) {
      return COMPANION.fromIdAndRecord(contractId, record$, agreementText, signatories, observers);
    }

    public static Contract fromCreatedEvent(CreatedEvent event) {
      return COMPANION.fromCreatedEvent(event);
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<AccountKey>> exerciseAccept(Accept arg) {
      return makeExerciseCmd(CHOICE_Accept, arg);
    }

    default Update<Exercised<AccountKey>> exerciseAccept(String label, String description,
        Factory.ContractId accountFactoryCid,
        com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId holdingFactoryCid,
        List<String> observers) {
      return exerciseAccept(new Accept(label, description, accountFactoryCid, holdingFactoryCid,
          observers));
    }

    default Update<Exercised<Unit>> exerciseDecline(Decline arg) {
      return makeExerciseCmd(CHOICE_Decline, arg);
    }

    default Update<Exercised<Unit>> exerciseDecline() {
      return exerciseDecline(new Decline());
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }

    default Update<Exercised<Unit>> exerciseWithdraw(Withdraw arg) {
      return makeExerciseCmd(CHOICE_Withdraw, arg);
    }

    default Update<Exercised<Unit>> exerciseWithdraw() {
      return exerciseWithdraw(new Withdraw());
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd implements Exercises<CreateAndExerciseCommand> {
    CreateAnd(Template createArguments) {
      super(createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Request, ?> getCompanion(
        ) {
      return COMPANION;
    }
  }
}
