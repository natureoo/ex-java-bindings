package com.daml.quickstart.model.daml.finance.settlement.routeprovider.singlecustodian;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider.RouteProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class SingleCustodian extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("252a8b6233ce5a616d582dc0b88449a7a11f5d03bf9709570f02ef8e6c28be94", "Daml.Finance.Settlement.RouteProvider.SingleCustodian", "SingleCustodian");

  public static final Choice<SingleCustodian, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, SingleCustodian> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.settlement.routeprovider.singlecustodian.SingleCustodian",
        TEMPLATE_ID, ContractId::new, v -> SingleCustodian.templateValueDecoder().decode(v),
        Contract::new, List.of(CHOICE_Archive));

  public final String provider;

  public final Set<String> observers;

  public final String custodian;

  public SingleCustodian(String provider, Set<String> observers, String custodian) {
    this.provider = provider;
    this.observers = observers;
    this.custodian = custodian;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<SingleCustodian>>> create(
      ) {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<SingleCustodian>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<SingleCustodian>>>(new CreateCommand(SingleCustodian.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<SingleCustodian>>> create(
      String provider, Set<String> observers, String custodian) {
    return new SingleCustodian(provider, observers, custodian).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, SingleCustodian> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static SingleCustodian fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<SingleCustodian> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(3);
    fields.add(new DamlRecord.Field("provider", new Party(this.provider)));
    fields.add(new DamlRecord.Field("observers", this.observers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("custodian", new Party(this.custodian)));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<SingleCustodian> templateValueDecoder() throws
      IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3, recordValue$);
      String provider = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      Set<String> observers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      String custodian = PrimitiveValueDecoders.fromParty.decode(fields$.get(2).getValue());
      return new SingleCustodian(provider, observers, custodian);
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
    if (!(object instanceof SingleCustodian)) {
      return false;
    }
    SingleCustodian other = (SingleCustodian) object;
    return Objects.equals(this.provider, other.provider) &&
        Objects.equals(this.observers, other.observers) &&
        Objects.equals(this.custodian, other.custodian);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.provider, this.observers, this.custodian);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.settlement.routeprovider.singlecustodian.SingleCustodian(%s, %s, %s)",
        this.provider, this.observers, this.custodian);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<SingleCustodian> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, SingleCustodian, ?> getCompanion(
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
        com.daml.ledger.javaapi.data.codegen.ContractId<SingleCustodian> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, SingleCustodian> {
    public Contract(ContractId id, SingleCustodian data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, SingleCustodian> getCompanion() {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, SingleCustodian, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public RouteProvider.CreateAnd toInterface(RouteProvider.INTERFACE_ interfaceCompanion) {
      return new RouteProvider.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
