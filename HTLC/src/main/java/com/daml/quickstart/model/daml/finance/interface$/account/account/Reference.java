package com.daml.quickstart.model.daml.finance.interface$.account.account;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;

import java.util.*;

public final class Reference extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("615590cf0b4e5ea01cb08ef577e43d6cfcd816be060ac7a540a100c3f00b11b2", "Daml.Finance.Interface.Account.Account", "Reference");

  public static final Choice<Reference, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final Choice<Reference, GetCid, Account.ContractId> CHOICE_GetCid = 
      Choice.create("GetCid", value$ -> value$.toValue(), value$ -> GetCid.valueDecoder()
        .decode(value$), value$ ->
        new Account.ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<Reference, SetCid, ContractId> CHOICE_SetCid = 
      Choice.create("SetCid", value$ -> value$.toValue(), value$ -> SetCid.valueDecoder()
        .decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<Reference, SetObservers, ContractId> CHOICE_SetObservers = 
      Choice.create("SetObservers", value$ -> value$.toValue(), value$ ->
        SetObservers.valueDecoder().decode(value$), value$ ->
        new ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final ContractCompanion.WithKey<Contract, ContractId, Reference, AccountKey> COMPANION = 
      new ContractCompanion.WithKey<>(
        "com.daml.quickstart.model.daml.finance.interface$.account.account.Reference", TEMPLATE_ID,
        ContractId::new, v -> Reference.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive, CHOICE_GetCid, CHOICE_SetCid, CHOICE_SetObservers),
        e -> AccountKey.valueDecoder().decode(e));

  public final View accountView;

  public final Account.ContractId cid;

  public final Map<String, Set<String>> observers;

  public Reference(View accountView, Account.ContractId cid, Map<String, Set<String>> observers) {
    this.accountView = accountView;
    this.cid = cid;
    this.observers = observers;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Reference>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Reference>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Reference>>>(new CreateCommand(Reference.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseArchive} instead
   */
  @Deprecated
  public static Update<Exercised<Unit>> exerciseByKeyArchive(AccountKey key, Archive arg) {
    return byKey(key).exerciseArchive(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseGetCid} instead
   */
  @Deprecated
  public static Update<Exercised<Account.ContractId>> exerciseByKeyGetCid(AccountKey key,
      GetCid arg) {
    return byKey(key).exerciseGetCid(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseGetCid(viewer)} instead
   */
  @Deprecated
  public static Update<Exercised<Account.ContractId>> exerciseByKeyGetCid(AccountKey key,
      String viewer) {
    return byKey(key).exerciseGetCid(viewer);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseSetCid} instead
   */
  @Deprecated
  public static Update<Exercised<ContractId>> exerciseByKeySetCid(AccountKey key, SetCid arg) {
    return byKey(key).exerciseSetCid(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseSetCid(newCid)} instead
   */
  @Deprecated
  public static Update<Exercised<ContractId>> exerciseByKeySetCid(AccountKey key,
      Account.ContractId newCid) {
    return byKey(key).exerciseSetCid(newCid);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseSetObservers} instead
   */
  @Deprecated
  public static Update<Exercised<ContractId>> exerciseByKeySetObservers(AccountKey key,
      SetObservers arg) {
    return byKey(key).exerciseSetObservers(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code byKey(key).exerciseSetObservers(newObservers)} instead
   */
  @Deprecated
  public static Update<Exercised<ContractId>> exerciseByKeySetObservers(AccountKey key,
      Map<String, Set<String>> newObservers) {
    return byKey(key).exerciseSetObservers(newObservers);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseGetCid} instead
   */
  @Deprecated
  public Update<Exercised<Account.ContractId>> createAndExerciseGetCid(GetCid arg) {
    return createAnd().exerciseGetCid(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseGetCid} instead
   */
  @Deprecated
  public Update<Exercised<Account.ContractId>> createAndExerciseGetCid(String viewer) {
    return createAndExerciseGetCid(new GetCid(viewer));
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseSetCid} instead
   */
  @Deprecated
  public Update<Exercised<ContractId>> createAndExerciseSetCid(SetCid arg) {
    return createAnd().exerciseSetCid(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseSetCid} instead
   */
  @Deprecated
  public Update<Exercised<ContractId>> createAndExerciseSetCid(Account.ContractId newCid) {
    return createAndExerciseSetCid(new SetCid(newCid));
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseSetObservers} instead
   */
  @Deprecated
  public Update<Exercised<ContractId>> createAndExerciseSetObservers(SetObservers arg) {
    return createAnd().exerciseSetObservers(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseSetObservers} instead
   */
  @Deprecated
  public Update<Exercised<ContractId>> createAndExerciseSetObservers(
      Map<String, Set<String>> newObservers) {
    return createAndExerciseSetObservers(new SetObservers(newObservers));
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Reference>>> create(
      View accountView, Account.ContractId cid, Map<String, Set<String>> observers) {
    return new Reference(accountView, cid, observers).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithKey<Contract, ContractId, Reference, AccountKey> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Reference fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Reference> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(3);
    fields.add(new DamlRecord.Field("accountView", this.accountView.toValue()));
    fields.add(new DamlRecord.Field("cid", this.cid.toValue()));
    fields.add(new DamlRecord.Field("observers", this.observers.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Reference> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(3, recordValue$);
      View accountView = View.valueDecoder().decode(fields$.get(0).getValue());
      Account.ContractId cid =
          new Account.ContractId(fields$.get(1).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected cid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(2).getValue());
      return new Reference(accountView, cid, observers);
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
    if (!(object instanceof Reference)) {
      return false;
    }
    Reference other = (Reference) object;
    return Objects.equals(this.accountView, other.accountView) &&
        Objects.equals(this.cid, other.cid) && Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.accountView, this.cid, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.account.account.Reference(%s, %s, %s)",
        this.accountView, this.cid, this.observers);
  }

  /**
   * Set up an {@link ExerciseByKeyCommand}; invoke an {@code exercise} method on the result of
      this to finish creating the command, or convert to an interface first with {@code toInterface}
      to invoke an interface {@code exercise} method.
   */
  public static ByKey byKey(AccountKey key) {
    return new ByKey(key.toValue());
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Reference> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Reference, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Reference> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends ContractWithKey<ContractId, Reference, AccountKey> {
    public Contract(ContractId id, Reference data, Optional<String> agreementText,
        Optional<AccountKey> key, java.util.Set<String> signatories,
        java.util.Set<String> observers) {
      super(id, data, agreementText, key, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Reference> getCompanion() {
      return COMPANION;
    }

    public static Contract fromIdAndRecord(String contractId, DamlRecord record$,
        Optional<String> agreementText, Optional<AccountKey> key, java.util.Set<String> signatories,
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

    default Update<Exercised<Account.ContractId>> exerciseGetCid(GetCid arg) {
      return makeExerciseCmd(CHOICE_GetCid, arg);
    }

    default Update<Exercised<Account.ContractId>> exerciseGetCid(String viewer) {
      return exerciseGetCid(new GetCid(viewer));
    }

    default Update<Exercised<ContractId>> exerciseSetCid(SetCid arg) {
      return makeExerciseCmd(CHOICE_SetCid, arg);
    }

    default Update<Exercised<ContractId>> exerciseSetCid(Account.ContractId newCid) {
      return exerciseSetCid(new SetCid(newCid));
    }

    default Update<Exercised<ContractId>> exerciseSetObservers(SetObservers arg) {
      return makeExerciseCmd(CHOICE_SetObservers, arg);
    }

    default Update<Exercised<ContractId>> exerciseSetObservers(
        Map<String, Set<String>> newObservers) {
      return exerciseSetObservers(new SetObservers(newObservers));
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd implements Exercises<CreateAndExerciseCommand> {
    CreateAnd(Template createArguments) {
      super(createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Reference, ?> getCompanion(
        ) {
      return COMPANION;
    }
  }

  public static final class ByKey extends com.daml.ledger.javaapi.data.codegen.ByKey implements Exercises<ExerciseByKeyCommand> {
    ByKey(Value key) {
      super(key);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Reference, ?> getCompanion(
        ) {
      return COMPANION;
    }
  }
}
