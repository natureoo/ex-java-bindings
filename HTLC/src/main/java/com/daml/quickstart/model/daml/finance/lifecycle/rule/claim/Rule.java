package com.daml.quickstart.model.daml.finance.lifecycle.rule.claim;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.lifecycle.rule.claim.Claim;
import com.daml.quickstart.model.daml.finance.interface$.settlement.factory.Factory;
import com.daml.quickstart.model.daml.finance.interface$.settlement.routeprovider.RouteProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Rule extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("ce12e0ba5c426b6a8e72e0c353486b1a436e08e1c602fb8bc3760f2491f8da90", "Daml.Finance.Lifecycle.Rule.Claim", "Rule");

  public static final Choice<Rule, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Rule> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.lifecycle.rule.claim.Rule", TEMPLATE_ID,
        ContractId::new, v -> Rule.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive));

  public final Set<String> providers;

  public final Set<String> claimers;

  public final Set<String> settlers;

  public final RouteProvider.ContractId routeProviderCid;

  public final Factory.ContractId settlementFactoryCid;

  public final Boolean netInstructions;

  public Rule(Set<String> providers, Set<String> claimers, Set<String> settlers,
      RouteProvider.ContractId routeProviderCid, Factory.ContractId settlementFactoryCid,
      Boolean netInstructions) {
    this.providers = providers;
    this.claimers = claimers;
    this.settlers = settlers;
    this.routeProviderCid = routeProviderCid;
    this.settlementFactoryCid = settlementFactoryCid;
    this.netInstructions = netInstructions;
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
      Set<String> providers, Set<String> claimers, Set<String> settlers,
      RouteProvider.ContractId routeProviderCid, Factory.ContractId settlementFactoryCid,
      Boolean netInstructions) {
    return new Rule(providers, claimers, settlers, routeProviderCid, settlementFactoryCid,
        netInstructions).create();
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
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(6);
    fields.add(new DamlRecord.Field("providers", this.providers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("claimers", this.claimers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("settlers", this.settlers.toValue(v$0 -> new Party(v$0))));
    fields.add(new DamlRecord.Field("routeProviderCid", this.routeProviderCid.toValue()));
    fields.add(new DamlRecord.Field("settlementFactoryCid", this.settlementFactoryCid.toValue()));
    fields.add(new DamlRecord.Field("netInstructions", Bool.of(this.netInstructions)));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Rule> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(6, recordValue$);
      Set<String> providers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Set<String> claimers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(1).getValue());
      Set<String> settlers = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(2).getValue());
      RouteProvider.ContractId routeProviderCid =
          new RouteProvider.ContractId(fields$.get(3).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected routeProviderCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Factory.ContractId settlementFactoryCid =
          new Factory.ContractId(fields$.get(4).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected settlementFactoryCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Boolean netInstructions = PrimitiveValueDecoders.fromBool.decode(fields$.get(5).getValue());
      return new Rule(providers, claimers, settlers, routeProviderCid, settlementFactoryCid,
          netInstructions);
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
        Objects.equals(this.claimers, other.claimers) &&
        Objects.equals(this.settlers, other.settlers) &&
        Objects.equals(this.routeProviderCid, other.routeProviderCid) &&
        Objects.equals(this.settlementFactoryCid, other.settlementFactoryCid) &&
        Objects.equals(this.netInstructions, other.netInstructions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.providers, this.claimers, this.settlers, this.routeProviderCid,
        this.settlementFactoryCid, this.netInstructions);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.lifecycle.rule.claim.Rule(%s, %s, %s, %s, %s, %s)",
        this.providers, this.claimers, this.settlers, this.routeProviderCid,
        this.settlementFactoryCid, this.netInstructions);
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

    public Claim.ContractId toInterface(Claim.INTERFACE_ interfaceCompanion) {
      return new Claim.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(Claim.ContractId interfaceContractId) {
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

    public Claim.CreateAnd toInterface(Claim.INTERFACE_ interfaceCompanion) {
      return new Claim.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
