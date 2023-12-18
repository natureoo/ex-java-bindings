package com.daml.quickstart.model.daml.finance.lifecycle.event.distribution;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Quantity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Event extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("ce12e0ba5c426b6a8e72e0c353486b1a436e08e1c602fb8bc3760f2491f8da90", "Daml.Finance.Lifecycle.Event.Distribution", "Event");

  public static final Choice<Event, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Event> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.lifecycle.event.distribution.Event", TEMPLATE_ID,
        ContractId::new, v -> Event.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive));

  public final Set<String> providers;

  public final Id id;

  public final String description;

  public final Instant effectiveTime;

  public final InstrumentKey targetInstrument;

  public final InstrumentKey newInstrument;

  public final List<Quantity<InstrumentKey, BigDecimal>> perUnitDistribution;

  public final Set<String> observers;

  public Event(Set<String> providers, Id id, String description, Instant effectiveTime,
      InstrumentKey targetInstrument, InstrumentKey newInstrument,
      List<Quantity<InstrumentKey, BigDecimal>> perUnitDistribution, Set<String> observers) {
    this.providers = providers;
    this.id = id;
    this.description = description;
    this.effectiveTime = effectiveTime;
    this.targetInstrument = targetInstrument;
    this.newInstrument = newInstrument;
    this.perUnitDistribution = perUnitDistribution;
    this.observers = observers;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Event>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Event>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Event>>>(new CreateCommand(Event.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Event>>> create(
      Set<String> providers, Id id, String description, Instant effectiveTime,
      InstrumentKey targetInstrument, InstrumentKey newInstrument,
      List<Quantity<InstrumentKey, BigDecimal>> perUnitDistribution, Set<String> observers) {
    return new Event(providers, id, description, effectiveTime, targetInstrument, newInstrument,
        perUnitDistribution, observers).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Event> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Event fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Event> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(8);
    fields.add(new DamlRecord.Field("providers", this.providers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("id", this.id.toValue()));
    fields.add(new DamlRecord.Field("description", new Text(this.description)));
    fields.add(new DamlRecord.Field("effectiveTime", Timestamp.fromInstant(this.effectiveTime)));
    fields.add(new DamlRecord.Field("targetInstrument", this.targetInstrument.toValue()));
    fields.add(new DamlRecord.Field("newInstrument", this.newInstrument.toValue()));
    fields.add(new DamlRecord.Field("perUnitDistribution", this.perUnitDistribution.stream().collect(DamlCollectors.toDamlList(v$0 -> v$0.toValue(v$1 -> v$1.toValue(),
        v$2 -> new Numeric(v$2))))));
    fields.add(new DamlRecord.Field("observers", this.observers.toValue(v$0 -> new Party(v$0))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Event> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(8, recordValue$);
      Set<String> providers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(1).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(2).getValue());
      Instant effectiveTime = PrimitiveValueDecoders.fromTimestamp
          .decode(fields$.get(3).getValue());
      InstrumentKey targetInstrument = InstrumentKey.valueDecoder()
          .decode(fields$.get(4).getValue());
      InstrumentKey newInstrument = InstrumentKey.valueDecoder().decode(fields$.get(5).getValue());
      List<Quantity<InstrumentKey, BigDecimal>> perUnitDistribution =
          PrimitiveValueDecoders.fromList(
            Quantity.<InstrumentKey,
            BigDecimal>valueDecoder(InstrumentKey.valueDecoder(),
            PrimitiveValueDecoders.fromNumeric)).decode(fields$.get(6).getValue());
      Set<String> observers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(7).getValue());
      return new Event(providers, id, description, effectiveTime, targetInstrument, newInstrument,
          perUnitDistribution, observers);
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
    if (!(object instanceof Event)) {
      return false;
    }
    Event other = (Event) object;
    return Objects.equals(this.providers, other.providers) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.effectiveTime, other.effectiveTime) &&
        Objects.equals(this.targetInstrument, other.targetInstrument) &&
        Objects.equals(this.newInstrument, other.newInstrument) &&
        Objects.equals(this.perUnitDistribution, other.perUnitDistribution) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.providers, this.id, this.description, this.effectiveTime,
        this.targetInstrument, this.newInstrument, this.perUnitDistribution, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.lifecycle.event.distribution.Event(%s, %s, %s, %s, %s, %s, %s, %s)",
        this.providers, this.id, this.description, this.effectiveTime, this.targetInstrument,
        this.newInstrument, this.perUnitDistribution, this.observers);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Event> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Event, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.Event.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.Event.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.Event.ContractId(this.contractId);
    }

    public com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.Event.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Event> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Event> {
    public Contract(ContractId id, Event data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Event> getCompanion() {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Event, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.Event.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.Event.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.distribution.Event.CreateAnd(COMPANION, this.createArguments);
    }

    public com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.lifecycle.event.Event.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
