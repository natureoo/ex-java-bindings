package com.daml.quickstart.model.daml.finance.holding.nonfungible;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.util.disclosure.Disclosure;

import java.util.*;

public final class Factory extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("4015bfc4b265c0711fe65640b70227f3f5f66b226ff1d2fd22a803434bc6227c", "Daml.Finance.Holding.NonFungible", "Factory");

  public static final Choice<Factory, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Factory> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.holding.nonfungible.Factory", TEMPLATE_ID,
        ContractId::new, v -> Factory.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive));

  public final String provider;

  public final Map<String, Set<String>> observers;

  public Factory(String provider, Map<String, Set<String>> observers) {
    this.provider = provider;
    this.observers = observers;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Factory>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Factory>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Factory>>>(new CreateCommand(Factory.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Factory>>> create(
      String provider, Map<String, Set<String>> observers) {
    return new Factory(provider, observers).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Factory> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Factory fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Factory> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(2);
    fields.add(new DamlRecord.Field("provider", new Party(this.provider)));
    fields.add(new DamlRecord.Field("observers", this.observers.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Factory> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2, recordValue$);
      String provider = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(1).getValue());
      return new Factory(provider, observers);
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
    if (!(object instanceof Factory)) {
      return false;
    }
    Factory other = (Factory) object;
    return Objects.equals(this.provider, other.provider) &&
        Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.provider, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.holding.nonfungible.Factory(%s, %s)",
        this.provider, this.observers);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Factory> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Factory, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public Disclosure.ContractId toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.ContractId(this.contractId);
    }

    public com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(Disclosure.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Factory> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Factory> {
    public Contract(ContractId id, Factory data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Factory> getCompanion() {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Factory, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public Disclosure.CreateAnd toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.CreateAnd(COMPANION, this.createArguments);
    }

    public com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.holding.factory.Factory.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
