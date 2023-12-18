package com.daml.quickstart.model.workflow.dvp;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.types.Tuple3;
import com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch;
import com.daml.quickstart.model.daml.finance.interface$.settlement.factory.Factory;
import com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction;
import com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider.RouteProvider;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Quantity;

import java.math.BigDecimal;
import java.util.*;

public final class Proposal extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("91deeb52981e7064dbcf05e8dd2a81ef0d8a25fe4aaff70e56ec84fcb11aa28d", "Workflow.DvP", "Proposal");

  public static final Choice<Proposal, Accept, Tuple3<Batch.ContractId, Instruction.ContractId, Instruction.ContractId>> CHOICE_Accept = 
      Choice.create("Accept", value$ -> value$.toValue(), value$ -> Accept.valueDecoder()
        .decode(value$), value$ ->
        Tuple3.<Batch.ContractId,
        Instruction.ContractId,
        Instruction.ContractId>valueDecoder(v$0 ->
          new Batch.ContractId(v$0.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()),
        v$1 ->
          new Instruction.ContractId(v$1.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()),
        v$2 ->
          new Instruction.ContractId(v$2.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()))
        .decode(value$));

  public static final Choice<Proposal, Decline, Unit> CHOICE_Decline = 
      Choice.create("Decline", value$ -> value$.toValue(), value$ -> Decline.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Proposal, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Proposal, Withdraw, Unit> CHOICE_Withdraw = 
      Choice.create("Withdraw", value$ -> value$.toValue(), value$ -> Withdraw.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Proposal> COMPANION = 
      new ContractCompanion.WithoutKey<>("com.daml.quickstart.model.workflow.dvp.Proposal",
        TEMPLATE_ID, ContractId::new, v -> Proposal.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Accept, CHOICE_Decline, CHOICE_Archive, CHOICE_Withdraw));

  public final Quantity<InstrumentKey, BigDecimal> recQuantity;

  public final Quantity<InstrumentKey, BigDecimal> payQuantity;

  public final String proposer;

  public final String counterparty;

  public final RouteProvider.ContractId routeProviderCid;

  public final Factory.ContractId settlementFactoryCid;

  public final String id;

  public Proposal(Quantity<InstrumentKey, BigDecimal> recQuantity,
      Quantity<InstrumentKey, BigDecimal> payQuantity, String proposer, String counterparty,
      RouteProvider.ContractId routeProviderCid, Factory.ContractId settlementFactoryCid,
      String id) {
    this.recQuantity = recQuantity;
    this.payQuantity = payQuantity;
    this.proposer = proposer;
    this.counterparty = counterparty;
    this.routeProviderCid = routeProviderCid;
    this.settlementFactoryCid = settlementFactoryCid;
    this.id = id;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Proposal>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Proposal>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Proposal>>>(new CreateCommand(Proposal.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseAccept} instead
   */
  @Deprecated
  public Update<Exercised<Tuple3<Batch.ContractId, Instruction.ContractId, Instruction.ContractId>>> createAndExerciseAccept(
      Accept arg) {
    return createAnd().exerciseAccept(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseAccept} instead
   */
  @Deprecated
  public Update<Exercised<Tuple3<Batch.ContractId, Instruction.ContractId, Instruction.ContractId>>> createAndExerciseAccept(
      ) {
    return createAndExerciseAccept(new Accept());
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

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Proposal>>> create(
      Quantity<InstrumentKey, BigDecimal> recQuantity,
      Quantity<InstrumentKey, BigDecimal> payQuantity, String proposer, String counterparty,
      RouteProvider.ContractId routeProviderCid, Factory.ContractId settlementFactoryCid,
      String id) {
    return new Proposal(recQuantity, payQuantity, proposer, counterparty, routeProviderCid,
        settlementFactoryCid, id).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Proposal> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Proposal fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Proposal> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(7);
    fields.add(new DamlRecord.Field("recQuantity", this.recQuantity.toValue(v$0 -> v$0.toValue(),
        v$1 -> new Numeric(v$1))));
    fields.add(new DamlRecord.Field("payQuantity", this.payQuantity.toValue(v$0 -> v$0.toValue(),
        v$1 -> new Numeric(v$1))));
    fields.add(new DamlRecord.Field("proposer", new Party(this.proposer)));
    fields.add(new DamlRecord.Field("counterparty", new Party(this.counterparty)));
    fields.add(new DamlRecord.Field("routeProviderCid", this.routeProviderCid.toValue()));
    fields.add(new DamlRecord.Field("settlementFactoryCid", this.settlementFactoryCid.toValue()));
    fields.add(new DamlRecord.Field("id", new Text(this.id)));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Proposal> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(7, recordValue$);
      Quantity<InstrumentKey, BigDecimal> recQuantity =
          Quantity.<InstrumentKey,
          BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
          PrimitiveValueDecoders.fromNumeric).decode(fields$.get(0).getValue());
      Quantity<InstrumentKey, BigDecimal> payQuantity =
          Quantity.<InstrumentKey,
          BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
          PrimitiveValueDecoders.fromNumeric).decode(fields$.get(1).getValue());
      String proposer = PrimitiveValueDecoders.fromParty.decode(fields$.get(2).getValue());
      String counterparty = PrimitiveValueDecoders.fromParty.decode(fields$.get(3).getValue());
      RouteProvider.ContractId routeProviderCid =
          new RouteProvider.ContractId(fields$.get(4).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected routeProviderCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Factory.ContractId settlementFactoryCid =
          new Factory.ContractId(fields$.get(5).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected settlementFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      String id = PrimitiveValueDecoders.fromText.decode(fields$.get(6).getValue());
      return new Proposal(recQuantity, payQuantity, proposer, counterparty, routeProviderCid,
          settlementFactoryCid, id);
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
    if (!(object instanceof Proposal)) {
      return false;
    }
    Proposal other = (Proposal) object;
    return Objects.equals(this.recQuantity, other.recQuantity) &&
        Objects.equals(this.payQuantity, other.payQuantity) &&
        Objects.equals(this.proposer, other.proposer) &&
        Objects.equals(this.counterparty, other.counterparty) &&
        Objects.equals(this.routeProviderCid, other.routeProviderCid) &&
        Objects.equals(this.settlementFactoryCid, other.settlementFactoryCid) &&
        Objects.equals(this.id, other.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.recQuantity, this.payQuantity, this.proposer, this.counterparty,
        this.routeProviderCid, this.settlementFactoryCid, this.id);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.workflow.dvp.Proposal(%s, %s, %s, %s, %s, %s, %s)",
        this.recQuantity, this.payQuantity, this.proposer, this.counterparty, this.routeProviderCid,
        this.settlementFactoryCid, this.id);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Proposal> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Proposal, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Proposal> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Proposal> {
    public Contract(ContractId id, Proposal data, Optional<String> agreementText,
        Set<String> signatories, Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Proposal> getCompanion() {
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
    default Update<Exercised<Tuple3<Batch.ContractId, Instruction.ContractId, Instruction.ContractId>>> exerciseAccept(
        Accept arg) {
      return makeExerciseCmd(CHOICE_Accept, arg);
    }

    default Update<Exercised<Tuple3<Batch.ContractId, Instruction.ContractId, Instruction.ContractId>>> exerciseAccept(
        ) {
      return exerciseAccept(new Accept());
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Proposal, ?> getCompanion(
        ) {
      return COMPANION;
    }
  }
}
