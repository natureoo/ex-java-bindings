package com.daml.quickstart.model.daml.finance.instrument.token.instrument;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;
import com.daml.quickstart.model.daml.finance.interface$.util.disclosure.Disclosure;

import java.time.Instant;
import java.util.*;

public final class Instrument extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("2cf62ae345caf0cd6f3ff0fe4a1e35a3605cc77dfbefb5b004bc8e6b8be0066e", "Daml.Finance.Instrument.Token.Instrument", "Instrument");

  public static final Choice<Instrument, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Instrument> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.instrument.token.instrument.Instrument",
        TEMPLATE_ID, ContractId::new, v -> Instrument.templateValueDecoder().decode(v),
        Contract::new, List.of(CHOICE_Archive));

  public final String depository;

  public final String issuer;

  public final Id id;

  public final String version;

  public final String description;

  public final Instant validAsOf;

  public final Map<String, Set<String>> observers;

  public Instrument(String depository, String issuer, Id id, String version, String description,
      Instant validAsOf, Map<String, Set<String>> observers) {
    this.depository = depository;
    this.issuer = issuer;
    this.id = id;
    this.version = version;
    this.description = description;
    this.validAsOf = validAsOf;
    this.observers = observers;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Instrument>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Instrument>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Instrument>>>(new CreateCommand(Instrument.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Instrument>>> create(
      String depository, String issuer, Id id, String version, String description,
      Instant validAsOf, Map<String, Set<String>> observers) {
    return new Instrument(depository, issuer, id, version, description, validAsOf,
        observers).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Instrument> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Instrument fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Instrument> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(7);
    fields.add(new DamlRecord.Field("depository", new Party(this.depository)));
    fields.add(new DamlRecord.Field("issuer", new Party(this.issuer)));
    fields.add(new DamlRecord.Field("id", this.id.toValue()));
    fields.add(new DamlRecord.Field("version", new Text(this.version)));
    fields.add(new DamlRecord.Field("description", new Text(this.description)));
    fields.add(new DamlRecord.Field("validAsOf", Timestamp.fromInstant(this.validAsOf)));
    fields.add(new DamlRecord.Field("observers", this.observers.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Instrument> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(7, recordValue$);
      String depository = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String issuer = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(2).getValue());
      String version = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(4).getValue());
      Instant validAsOf = PrimitiveValueDecoders.fromTimestamp.decode(fields$.get(5).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(6).getValue());
      return new Instrument(depository, issuer, id, version, description, validAsOf, observers);
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
    if (!(object instanceof Instrument)) {
      return false;
    }
    Instrument other = (Instrument) object;
    return Objects.equals(this.depository, other.depository) &&
        Objects.equals(this.issuer, other.issuer) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.version, other.version) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.validAsOf, other.validAsOf) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.depository, this.issuer, this.id, this.version, this.description,
        this.validAsOf, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.instrument.token.instrument.Instrument(%s, %s, %s, %s, %s, %s, %s)",
        this.depository, this.issuer, this.id, this.version, this.description, this.validAsOf,
        this.observers);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Instrument> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Instrument, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.instrument.token.instrument.Instrument.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.instrument.token.instrument.Instrument.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.instrument.token.instrument.Instrument.ContractId(this.contractId);
    }

    public Disclosure.ContractId toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.ContractId(this.contractId);
    }

    public com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.instrument.token.instrument.Instrument.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(Disclosure.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Instrument> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Instrument> {
    public Contract(ContractId id, Instrument data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Instrument> getCompanion() {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Instrument, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.instrument.token.instrument.Instrument.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.instrument.token.instrument.Instrument.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.instrument.token.instrument.Instrument.CreateAnd(COMPANION, this.createArguments);
    }

    public Disclosure.CreateAnd toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.CreateAnd(COMPANION, this.createArguments);
    }

    public com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.Instrument.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
