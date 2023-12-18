package com.daml.quickstart.model.daml.finance.holding.fungible;

import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.*;
import com.daml.ledger.javaapi.data.codegen.*;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Lock;
import com.daml.quickstart.model.daml.finance.interface$.holding.transferable.Transferable;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey;
import com.daml.quickstart.model.daml.finance.interface$.util.disclosure.Disclosure;

import java.math.BigDecimal;
import java.util.*;

public final class Fungible extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("4015bfc4b265c0711fe65640b70227f3f5f66b226ff1d2fd22a803434bc6227c", "Daml.Finance.Holding.Fungible", "Fungible");

  public static final Choice<Fungible, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, Fungible> COMPANION = 
      new ContractCompanion.WithoutKey<>(
        "com.daml.quickstart.model.daml.finance.holding.fungible.Fungible", TEMPLATE_ID,
        ContractId::new, v -> Fungible.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_Archive));

  public final InstrumentKey instrument;

  public final AccountKey account;

  public final BigDecimal amount;

  public final Optional<Lock> lock;

  public final Map<String, Set<String>> observers;

  public Fungible(InstrumentKey instrument, AccountKey account, BigDecimal amount,
      Optional<Lock> lock, Map<String, Set<String>> observers) {
    this.instrument = instrument;
    this.account = account;
    this.amount = amount;
    this.lock = lock;
    this.observers = observers;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Fungible>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<Fungible>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<Fungible>>>(new CreateCommand(Fungible.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<Fungible>>> create(
      InstrumentKey instrument, AccountKey account, BigDecimal amount, Optional<Lock> lock,
      Map<String, Set<String>> observers) {
    return new Fungible(instrument, account, amount, lock, observers).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, Fungible> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Fungible fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Fungible> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(5);
    fields.add(new DamlRecord.Field("instrument", this.instrument.toValue()));
    fields.add(new DamlRecord.Field("account", this.account.toValue()));
    fields.add(new DamlRecord.Field("amount", new Numeric(this.amount)));
    fields.add(new DamlRecord.Field("lock", DamlOptional.of(this.lock.map(v$0 -> v$0.toValue()))));
    fields.add(new DamlRecord.Field("observers", this.observers.entrySet().stream()
        .collect(DamlCollectors.toDamlGenMap(v$0 -> new Text(v$0.getKey()), v$0 -> v$0.getValue().toValue(v$1 -> new Party(v$1))))));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<Fungible> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(5, recordValue$);
      InstrumentKey instrument = InstrumentKey.valueDecoder().decode(fields$.get(0).getValue());
      AccountKey account = AccountKey.valueDecoder().decode(fields$.get(1).getValue());
      BigDecimal amount = PrimitiveValueDecoders.fromNumeric.decode(fields$.get(2).getValue());
      Optional<Lock> lock = PrimitiveValueDecoders.fromOptional(Lock.valueDecoder())
          .decode(fields$.get(3).getValue());
      Map<String, Set<String>> observers = PrimitiveValueDecoders.fromGenMap(
            PrimitiveValueDecoders.fromText,
            Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty))
          .decode(fields$.get(4).getValue());
      return new Fungible(instrument, account, amount, lock, observers);
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
    if (!(object instanceof Fungible)) {
      return false;
    }
    Fungible other = (Fungible) object;
    return Objects.equals(this.instrument, other.instrument) &&
        Objects.equals(this.account, other.account) && Objects.equals(this.amount, other.amount) &&
        Objects.equals(this.lock, other.lock) && Objects.equals(this.observers, other.observers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.instrument, this.account, this.amount, this.lock, this.observers);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.holding.fungible.Fungible(%s, %s, %s, %s, %s)",
        this.instrument, this.account, this.amount, this.lock, this.observers);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<Fungible> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Fungible, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible.ContractId toInterface(
        com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible.ContractId(this.contractId);
    }

    public Transferable.ContractId toInterface(Transferable.INTERFACE_ interfaceCompanion) {
      return new Transferable.ContractId(this.contractId);
    }

    public Disclosure.ContractId toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.ContractId(this.contractId);
    }

    public Base.ContractId toInterface(Base.INTERFACE_ interfaceCompanion) {
      return new Base.ContractId(this.contractId);
    }

    public static ContractId unsafeFromInterface(
        com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(Transferable.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(Disclosure.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId unsafeFromInterface(Base.ContractId interfaceContractId) {
      return new ContractId(interfaceContractId.contractId);
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<Fungible> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, Fungible> {
    public Contract(ContractId id, Fungible data, Optional<String> agreementText,
        java.util.Set<String> signatories, java.util.Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, Fungible> getCompanion() {
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
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, Fungible, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible.CreateAnd toInterface(
        com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible.INTERFACE_ interfaceCompanion) {
      return new com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Fungible.CreateAnd(COMPANION, this.createArguments);
    }

    public Transferable.CreateAnd toInterface(Transferable.INTERFACE_ interfaceCompanion) {
      return new Transferable.CreateAnd(COMPANION, this.createArguments);
    }

    public Disclosure.CreateAnd toInterface(Disclosure.INTERFACE_ interfaceCompanion) {
      return new Disclosure.CreateAnd(COMPANION, this.createArguments);
    }

    public Base.CreateAnd toInterface(Base.INTERFACE_ interfaceCompanion) {
      return new Base.CreateAnd(COMPANION, this.createArguments);
    }
  }
}
