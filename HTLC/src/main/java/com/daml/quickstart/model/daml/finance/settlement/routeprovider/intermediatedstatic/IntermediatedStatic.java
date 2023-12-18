package com.daml.quickstart.model.daml.finance.settlement.routeprovider.intermediatedstatic;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider.RouteProvider;
import com.daml.quickstart.model.daml.finance.settlement.hierarchy.Hierarchy;

import java.util.*;

public final class IntermediatedStatic extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("252a8b6233ce5a616d582dc0b88449a7a11f5d03bf9709570f02ef8e6c28be94", "Daml.Finance.Settlement.RouteProvider.IntermediatedStatic", "IntermediatedStatic");

  public static final Choice<IntermediatedStatic, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, IntermediatedStatic> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.settlement.routeprovider.intermediatedstatic.IntermediatedStatic",
        TEMPLATE_ID, ContractId::new, v -> IntermediatedStatic.templateValueDecoder().decode(v),
        Contract::new, List.of(CHOICE_Archive));

  public final String provider;

  public final Set<String> observers;

  public final Map<String, Hierarchy> paths;

  public IntermediatedStatic(String provider, Set<String> observers, Map<String, Hierarchy> paths) {
    this.provider = provider;
    this.observers = observers;
    this.paths = paths;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<IntermediatedStatic>>> create(
      ) {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<IntermediatedStatic>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<IntermediatedStatic>>>(new CreateCommand(IntermediatedStatic.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<IntermediatedStatic>>> create(
      String provider, Set<String> observers, Map<String, Hierarchy> paths) {
    return new IntermediatedStatic(provider, observers, paths).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, IntermediatedStatic> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static IntermediatedStatic fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<IntermediatedStatic> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(3);
    fields.add(new DamlRecord.Field("provider", new Party(this.provider)));
    fields.add(new DamlRecord.Field("observers", this.observers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("paths", this.paths.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue()))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<IntermediatedStatic> templateValueDecoder() throws
      IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3, recordValue$);
      String provider = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      Set<String> observers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      Map<String, Hierarchy> paths = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText, Hierarchy.valueDecoder())
          .decode(fields$.get(2).getValue());
      return new IntermediatedStatic(provider, observers, paths);
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
    if (!(object instanceof IntermediatedStatic)) {
      return false;
    }
    IntermediatedStatic other = (IntermediatedStatic) object;
    return Objects.equals(this.provider, other.provider) &&
        Objects.equals(this.observers, other.observers) && Objects.equals(this.paths, other.paths);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.provider, this.observers, this.paths);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.settlement.routeprovider.intermediatedstatic.IntermediatedStatic(%s, %s, %s)",
        this.provider, this.observers, this.paths);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<IntermediatedStatic> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, IntermediatedStatic, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public RouteProvider.ContractId toInterface(RouteProvider.INTERFACE_ interfaceCompanion) {
      return new RouteProvider.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(RouteProvider.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<IntermediatedStatic> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, IntermediatedStatic> {
    public Contract(ContractId id, IntermediatedStatic data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, IntermediatedStatic> getCompanion() {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, IntermediatedStatic, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public RouteProvider.CreateAnd toInterface(RouteProvider.INTERFACE_ interfaceCompanion) {
      return new RouteProvider.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
