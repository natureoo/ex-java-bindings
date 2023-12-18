package com.daml.quickstart.model.daml.finance.settlement.batch;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.da.types.Tuple2;
import com.daml.quickstart.model.daml.finance.interface$.settlement.types.RoutedStep;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Batch extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("252a8b6233ce5a616d582dc0b88449a7a11f5d03bf9709570f02ef8e6c28be94", "Daml.Finance.Settlement.Batch", "Batch");

  public static final Choice<Batch, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithKey<Contract, ContractId, Batch, BatchKey> COMPANION = 
      new ContractCompanion.WithKey<>(
        "com.daml.quickstart.model.daml.finance.settlement.batch.Batch", TEMPLATE_ID,
        ContractId::new, v -> Batch.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive), e -> BatchKey.valueDecoder().decode(e));

  public final Set<String> requestors;

  public final Set<String> settlers;

  public final Id id;

  public final String description;

  public final Optional<Id> contextId;

  public final List<Tuple2<RoutedStep, Id>> routedStepsWithInstructionId;

  public final Optional<Instant> settlementTime;

  public Batch(Set<String> requestors, Set<String> settlers, Id id, String description,
      Optional<Id> contextId, List<Tuple2<RoutedStep, Id>> routedStepsWithInstructionId,
      Optional<Instant> settlementTime) {
    this.requestors = requestors;
    this.settlers = settlers;
    this.id = id;
    this.description = description;
    this.contextId = contextId;
    this.routedStepsWithInstructionId = routedStepsWithInstructionId;
    this.settlementTime = settlementTime;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Batch>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Batch>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Batch>>>(new CreateCommand(Batch.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseArchive} instead
   */
  @Deprecated
  public static Update<Exercised<Unit>> exerciseByKeyArchive(BatchKey key, Archive arg) {
    return byKey(key).exerciseArchive(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Batch>>> create(
      Set<String> requestors, Set<String> settlers, Id id, String description,
      Optional<Id> contextId, List<Tuple2<RoutedStep, Id>> routedStepsWithInstructionId,
      Optional<Instant> settlementTime) {
    return new Batch(requestors, settlers, id, description, contextId, routedStepsWithInstructionId,
        settlementTime).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithKey<Contract, ContractId, Batch, BatchKey> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Batch fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Batch> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(7);
    fields.add(new DamlRecord.Field("requestors", this.requestors.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("settlers", this.settlers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("id", this.id.toValue()));
    fields.add(new DamlRecord.Field("description", new Text(this.description)));
    fields.add(new DamlRecord.Field("contextId", DamlOptional.of(this.contextId.map(v$0 -> v$0.toValue()))));
    fields.add(new DamlRecord.Field("routedStepsWithInstructionId", this.routedStepsWithInstructionId.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue(v$1 -> v$1.toValue(),
        v$2 -> v$2.toValue())))));
    fields.add(new DamlRecord.Field("settlementTime", DamlOptional.of(this.settlementTime.map(v$0 -> Timestamp.fromInstant(v$0)))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Batch> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(7, recordValue$);
      Set<String> requestors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Set<String> settlers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(2).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      Optional<Id> contextId = PrimitiveValueDecoders.fromOptional(Id.valueDecoder())
          .decode(fields$.get(4).getValue());
      List<Tuple2<RoutedStep, Id>> routedStepsWithInstructionId = PrimitiveValueDecoders.fromList(
            Tuple2.<RoutedStep,
            Id>valueDecoder(RoutedStep.valueDecoder(),
            Id.valueDecoder())).decode(fields$.get(5).getValue());
      Optional<Instant> settlementTime = PrimitiveValueDecoders.fromOptional(
            PrimitiveValueDecoders.fromTimestamp).decode(fields$.get(6).getValue());
      return new Batch(requestors, settlers, id, description, contextId,
          routedStepsWithInstructionId, settlementTime);
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
    if (!(object instanceof Batch)) {
      return false;
    }
    Batch other = (Batch) object;
    return Objects.equals(this.requestors, other.requestors) &&
        Objects.equals(this.settlers, other.settlers) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.contextId, other.contextId) &&
        Objects.equals(this.routedStepsWithInstructionId, other.routedStepsWithInstructionId) &&
        Objects.equals(this.settlementTime, other.settlementTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.requestors, this.settlers, this.id, this.description, this.contextId,
        this.routedStepsWithInstructionId, this.settlementTime);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.settlement.batch.Batch(%s, %s, %s, %s, %s, %s, %s)",
        this.requestors, this.settlers, this.id, this.description, this.contextId,
        this.routedStepsWithInstructionId, this.settlementTime);
  }

  /**
   * Set up an {@link ExerciseByKeyCommand}; invoke an {@code exercise} method on the result of
      this to finish creating the command, or convert to an interface first with {@code toInterface}
      to invoke an interface {@code exercise} method.
   */
  public static ByKey byKey(BatchKey key) {
    return new ByKey(key.toValue());
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Batch> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Batch, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Batch> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends ContractWithKey<ContractId, Batch, BatchKey> {
    public Contract(ContractId id, Batch data, Optional<String> agreementText,
        Optional<BatchKey> key, java.util.Set<String> signatories,
        java.util.Set<String> observers) {
      super(id, data, agreementText, key, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Batch> getCompanion() {
      return COMPANION;
    }

    public static Contract fromIdAndRecord(String contractId, DamlRecord record$,
        Optional<String> agreementText, Optional<BatchKey> key, java.util.Set<String> signatories,
        java.util.Set<String> observers) {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Batch, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.CreateAnd(COMPANION, this.createArguments);
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey implements Exercises<ExerciseByKeyCommand> {
    ByKey(Value key) {
      super(key);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Batch, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.ByKey toInterface(
        com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.settlement.batch.Batch.ByKey(COMPANION, this.contractKey);
    }
  }
}
