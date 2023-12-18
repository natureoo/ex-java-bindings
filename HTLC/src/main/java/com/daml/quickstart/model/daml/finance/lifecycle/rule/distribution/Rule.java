package com.daml.quickstart.model.daml.finance.lifecycle.rule.distribution;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.lifecycle.Lifecycle;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Rule extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("ce12e0ba5c426b6a8e72e0c353486b1a436e08e1c602fb8bc3760f2491f8da90", "Daml.Finance.Lifecycle.Rule.Distribution", "Rule");

  public static final Choice<Rule, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Rule> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.lifecycle.rule.distribution.Rule", TEMPLATE_ID,
        ContractId::new, v -> Rule.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive));

  public final Set<String> providers;

  public final String lifecycler;

  public final Set<String> observers;

  public final Id id;

  public final String description;

  public Rule(Set<String> providers, String lifecycler, Set<String> observers, Id id,
      String description) {
    this.providers = providers;
    this.lifecycler = lifecycler;
    this.observers = observers;
    this.id = id;
    this.description = description;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Rule>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Rule>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Rule>>>(new CreateCommand(Rule.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Rule>>> create(
      Set<String> providers, String lifecycler, Set<String> observers, Id id, String description) {
    return new Rule(providers, lifecycler, observers, id, description).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Rule> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Rule fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Rule> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(5);
    fields.add(new DamlRecord.Field("providers", this.providers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("lifecycler", new Party(this.lifecycler)));
    fields.add(new DamlRecord.Field("observers", this.observers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("id", this.id.toValue()));
    fields.add(new DamlRecord.Field("description", new Text(this.description)));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Rule> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(5, recordValue$);
      Set<String> providers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      String lifecycler = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Set<String> observers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(2).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(3).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(4).getValue());
      return new Rule(providers, lifecycler, observers, id, description);
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
    if (!(object instanceof Rule)) {
      return false;
    }
    Rule other = (Rule) object;
    return Objects.equals(this.providers, other.providers) &&
        Objects.equals(this.lifecycler, other.lifecycler) &&
        Objects.equals(this.observers, other.observers) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.description, other.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.providers, this.lifecycler, this.observers, this.id, this.description);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.lifecycle.rule.distribution.Rule(%s, %s, %s, %s, %s)",
        this.providers, this.lifecycler, this.observers, this.id, this.description);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Rule> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Rule, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public Lifecycle.ContractId toInterface(Lifecycle.INTERFACE_ interfaceCompanion) {
      return new Lifecycle.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(Lifecycle.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Rule> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Rule> {
    public Contract(ContractId id, Rule data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Rule> getCompanion() {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Rule, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public Lifecycle.CreateAnd toInterface(Lifecycle.INTERFACE_ interfaceCompanion) {
      return new Lifecycle.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
