package com.daml.quickstart.model.daml.finance.settlement.instruction;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Allocation;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.Approval;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.InstructionKey;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.RoutedStep;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;
import com.daml.quickstart.model.daml.finance.interface$.util.disclosure.Disclosure;

import java.time.Instant;
import java.util.*;

public final class Instruction extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("252a8b6233ce5a616d582dc0b88449a7a11f5d03bf9709570f02ef8e6c28be94", "Daml.Finance.Settlement.Instruction", "Instruction");

  public static final Choice<Instruction, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithKey<Contract, ContractId, Instruction, InstructionKey> COMPANION = 
      new ContractCompanion.WithKey<>(
        "com.daml.quickstart.model.daml.finance.settlement.instruction.Instruction", TEMPLATE_ID,
        ContractId::new, v -> Instruction.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive), e -> InstructionKey.valueDecoder().decode(e));

  public final Set<String> requestors;

  public final Set<String> settlers;

  public final Id batchId;

  public final Id id;

  public final RoutedStep routedStep;

  public final Optional<Instant> settlementTime;

  public final Allocation allocation;

  public final Approval approval;

  public final Set<String> signedSenders;

  public final Set<String> signedReceivers;

  public final Map<String, Set<String>> observers;

  public Instruction(Set<String> requestors, Set<String> settlers, Id batchId, Id id,
      RoutedStep routedStep, Optional<Instant> settlementTime, Allocation allocation,
      Approval approval, Set<String> signedSenders, Set<String> signedReceivers,
      Map<String, Set<String>> observers) {
    this.requestors = requestors;
    this.settlers = settlers;
    this.batchId = batchId;
    this.id = id;
    this.routedStep = routedStep;
    this.settlementTime = settlementTime;
    this.allocation = allocation;
    this.approval = approval;
    this.signedSenders = signedSenders;
    this.signedReceivers = signedReceivers;
    this.observers = observers;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Instruction>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Instruction>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Instruction>>>(new CreateCommand(Instruction.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseArchive} instead
   */
  @Deprecated
  public static Update<Exercised<Unit>> exerciseByKeyArchive(InstructionKey key, Archive arg) {
    return byKey(key).exerciseArchive(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Instruction>>> create(
      Set<String> requestors, Set<String> settlers, Id batchId, Id id, RoutedStep routedStep,
      Optional<Instant> settlementTime, Allocation allocation, Approval approval,
      Set<String> signedSenders, Set<String> signedReceivers, Map<String, Set<String>> observers) {
    return new Instruction(requestors, settlers, batchId, id, routedStep, settlementTime,
        allocation, approval, signedSenders, signedReceivers, observers).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithKey<Contract, ContractId, Instruction, InstructionKey> getCompanion(
      ) {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Instruction fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Instruction> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(11);
    fields.add(new DamlRecord.Field("requestors", this.requestors.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("settlers", this.settlers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("batchId", this.batchId.toValue()));
    fields.add(new DamlRecord.Field("id", this.id.toValue()));
    fields.add(new DamlRecord.Field("routedStep", this.routedStep.toValue()));
    fields.add(new DamlRecord.Field("settlementTime", DamlOptional.of(this.settlementTime.map(v$0 -> Timestamp.fromInstant(v$0)))));
    fields.add(new DamlRecord.Field("allocation", this.allocation.toValue()));
    fields.add(new DamlRecord.Field("approval", this.approval.toValue()));
    fields.add(new DamlRecord.Field("signedSenders", this.signedSenders.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("signedReceivers", this.signedReceivers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("observers", this.observers.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Instruction> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(11, recordValue$);
      Set<String> requestors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Set<String> settlers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      Id batchId = Id.valueDecoder().decode(fields$.get(2).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(3).getValue());
      RoutedStep routedStep = RoutedStep.valueDecoder().decode(fields$.get(4).getValue());
      Optional<Instant> settlementTime = PrimitiveValueDecoders.fromOptional(
            PrimitiveValueDecoders.fromTimestamp).decode(fields$.get(5).getValue());
      Allocation allocation = Allocation.valueDecoder().decode(fields$.get(6).getValue());
      Approval approval = Approval.valueDecoder().decode(fields$.get(7).getValue());
      Set<String> signedSenders =
          Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(8).getValue());
      Set<String> signedReceivers =
          Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(9).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(10).getValue());
      return new Instruction(requestors, settlers, batchId, id, routedStep, settlementTime,
          allocation, approval, signedSenders, signedReceivers, observers);
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
    if (!(object instanceof Instruction)) {
      return false;
    }
    Instruction other = (Instruction) object;
    return Objects.equals(this.requestors, other.requestors) &&
        Objects.equals(this.settlers, other.settlers) &&
        Objects.equals(this.batchId, other.batchId) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.routedStep, other.routedStep) &&
        Objects.equals(this.settlementTime, other.settlementTime) &&
        Objects.equals(this.allocation, other.allocation) &&
        Objects.equals(this.approval, other.approval) &&
        Objects.equals(this.signedSenders, other.signedSenders) &&
        Objects.equals(this.signedReceivers, other.signedReceivers) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.requestors, this.settlers, this.batchId, this.id, this.routedStep,
        this.settlementTime, this.allocation, this.approval, this.signedSenders,
        this.signedReceivers, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.settlement.instruction.Instruction(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)",
        this.requestors, this.settlers, this.batchId, this.id, this.routedStep, this.settlementTime,
        this.allocation, this.approval, this.signedSenders, this.signedReceivers, this.observers);
  }

  /**
   * Set up an {@link ExerciseByKeyCommand}; invoke an {@code exercise} method on the result of
      this to finish creating the command, or convert to an interface first with {@code toInterface}
      to invoke an interface {@code exercise} method.
   */
  public static ByKey byKey(InstructionKey key) {
    return new ByKey(key.toValue());
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Instruction> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Instruction, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.ContractId(this.contractId);
    }

    public Disclosure.ContractId toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(Disclosure.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Instruction> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends ContractWithKey<ContractId, Instruction, InstructionKey> {
    public Contract(ContractId id, Instruction data, Optional<String> agreementText,
        Optional<InstructionKey> key, java.util.Set<String> signatories,
        java.util.Set<String> observers) {
      super(id, data, agreementText, key, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Instruction> getCompanion() {
      return COMPANION;
    }

    public static Contract fromIdAndRecord(String contractId, DamlRecord record$,
        Optional<String> agreementText, Optional<InstructionKey> key,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      return COMPANION.fromIdAndRecord(contractId, record$, agreementText, key, signatories,
          observers);
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Instruction, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.CreateAnd(COMPANION, this.createArguments);
    }

    public Disclosure.CreateAnd toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.CreateAnd(COMPANION, this.createArguments);
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey implements Exercises<ExerciseByKeyCommand> {
    ByKey(Value key) {
      super(key);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Instruction, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.ByKey toInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.settlement.instruction.Instruction.ByKey(COMPANION, this.contractKey);
    }

    public Disclosure.ByKey toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.ByKey(COMPANION, this.contractKey);
    }
  }
}
