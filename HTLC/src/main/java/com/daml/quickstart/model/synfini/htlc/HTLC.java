package com.daml.quickstart.model.synfini.htlc;

import com.daml.ledger.javaapi.data.ContractFilter;
import com.daml.ledger.javaapi.data.CreateAndExerciseCommand;
import com.daml.ledger.javaapi.data.CreateCommand;
import com.daml.ledger.javaapi.data.CreatedEvent;
import com.daml.ledger.javaapi.data.DamlRecord;
import com.daml.ledger.javaapi.data.ExerciseCommand;
import com.daml.ledger.javaapi.data.Identifier;
import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Template;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Timestamp;
import com.daml.ledger.javaapi.data.Unit;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.Choice;
import com.daml.ledger.javaapi.data.codegen.ContractCompanion;
import com.daml.ledger.javaapi.data.codegen.ContractTypeCompanion;
import com.daml.ledger.javaapi.data.codegen.Created;
import com.daml.ledger.javaapi.data.codegen.Exercised;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.Update;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.internal.template.Archive;
import com.daml.quickstart.model.daml.finance.interface$.holding.transferable.Transferable;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.AccountKey;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public final class HTLC extends Template {
  public static final Identifier TEMPLATE_ID = new Identifier("ed470a2759bcfa1304b57e1221c2f15bfe11e4b573ad92d640a73ccc1dccbda5", "Synfini.HTLC", "HTLC");

  public static final Choice<HTLC, HTLC_Claim, Transferable.ContractId> CHOICE_HTLC_Claim = 
      Choice.create("HTLC_Claim", value$ -> value$.toValue(), value$ -> HTLC_Claim.valueDecoder()
        .decode(value$), value$ ->
        new Transferable.ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<HTLC, HTLC_Unlock, Transferable.ContractId> CHOICE_HTLC_Unlock = 
      Choice.create("HTLC_Unlock", value$ -> value$.toValue(), value$ -> HTLC_Unlock.valueDecoder()
        .decode(value$), value$ ->
        new Transferable.ContractId(value$.asContractId().orElseThrow(() -> new IllegalArgumentException("Expected value$ to be of type com.daml.ledger.javaapi.data.ContractId")).getValue()));

  public static final Choice<HTLC, Archive, Unit> CHOICE_Archive = 
      Choice.create("Archive", value$ -> value$.toValue(), value$ -> Archive.valueDecoder()
        .decode(value$), value$ -> PrimitiveValueDecoders.fromUnit.decode(value$));

  public static final ContractCompanion.WithoutKey<Contract, ContractId, HTLC> COMPANION = 
      new ContractCompanion.WithoutKey<>("com.daml.quickstart.model.synfini.htlc.HTLC", TEMPLATE_ID,
        ContractId::new, v -> HTLC.templateValueDecoder().decode(v), Contract::new,
        List.of(CHOICE_HTLC_Claim, CHOICE_HTLC_Unlock, CHOICE_Archive));

  public final String sender;

  public final AccountKey receiverAccount;

  public final Transferable.ContractId holdingCid;

  public final String hash;

  public final Instant unlockTime;

  public HTLC(String sender, AccountKey receiverAccount, Transferable.ContractId holdingCid,
      String hash, Instant unlockTime) {
    this.sender = sender;
    this.receiverAccount = receiverAccount;
    this.holdingCid = holdingCid;
    this.hash = hash;
    this.unlockTime = unlockTime;
  }

  @Override
  public Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<HTLC>>> create() {
    return new Update.CreateUpdate<com.daml.ledger.javaapi.data.codegen.ContractId<HTLC>, Created<com.daml.ledger.javaapi.data.codegen.ContractId<HTLC>>>(new CreateCommand(HTLC.TEMPLATE_ID, this.toValue()), x -> x, ContractId::new);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseHTLC_Claim} instead
   */
  @Deprecated
  public Update<Exercised<Transferable.ContractId>> createAndExerciseHTLC_Claim(HTLC_Claim arg) {
    return createAnd().exerciseHTLC_Claim(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseHTLC_Claim} instead
   */
  @Deprecated
  public Update<Exercised<Transferable.ContractId>> createAndExerciseHTLC_Claim(String sharedHash) {
    return createAndExerciseHTLC_Claim(new HTLC_Claim(sharedHash));
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseHTLC_Unlock} instead
   */
  @Deprecated
  public Update<Exercised<Transferable.ContractId>> createAndExerciseHTLC_Unlock(HTLC_Unlock arg) {
    return createAnd().exerciseHTLC_Unlock(arg);
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseHTLC_Unlock} instead
   */
  @Deprecated
  public Update<Exercised<Transferable.ContractId>> createAndExerciseHTLC_Unlock() {
    return createAndExerciseHTLC_Unlock(new HTLC_Unlock());
  }

  /**
   * @deprecated since Daml 2.3.0; use {@code createAnd().exerciseArchive} instead
   */
  @Deprecated
  public Update<Exercised<Unit>> createAndExerciseArchive(Archive arg) {
    return createAnd().exerciseArchive(arg);
  }

  public static Update<Created<com.daml.ledger.javaapi.data.codegen.ContractId<HTLC>>> create(
      String sender, AccountKey receiverAccount, Transferable.ContractId holdingCid, String hash,
      Instant unlockTime) {
    return new HTLC(sender, receiverAccount, holdingCid, hash, unlockTime).create();
  }

  @Override
  public CreateAnd createAnd() {
    return new CreateAnd(this);
  }

  @Override
  protected ContractCompanion.WithoutKey<Contract, ContractId, HTLC> getCompanion() {
    return COMPANION;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static HTLC fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<HTLC> valueDecoder() throws IllegalArgumentException {
    return ContractCompanion.valueDecoder(COMPANION);
  }

  public DamlRecord toValue() {
    ArrayList<DamlRecord.Field> fields = new ArrayList<DamlRecord.Field>(5);
    fields.add(new DamlRecord.Field("sender", new Party(this.sender)));
    fields.add(new DamlRecord.Field("receiverAccount", this.receiverAccount.toValue()));
    fields.add(new DamlRecord.Field("holdingCid", this.holdingCid.toValue()));
    fields.add(new DamlRecord.Field("hash", new Text(this.hash)));
    fields.add(new DamlRecord.Field("unlockTime", Timestamp.fromInstant(this.unlockTime)));
    return new DamlRecord(fields);
  }

  private static ValueDecoder<HTLC> templateValueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(5, recordValue$);
      String sender = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      AccountKey receiverAccount = AccountKey.valueDecoder().decode(fields$.get(1).getValue());
      Transferable.ContractId holdingCid =
          new Transferable.ContractId(fields$.get(2).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      String hash = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      Instant unlockTime = PrimitiveValueDecoders.fromTimestamp.decode(fields$.get(4).getValue());
      return new HTLC(sender, receiverAccount, holdingCid, hash, unlockTime);
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
    if (!(object instanceof HTLC)) {
      return false;
    }
    HTLC other = (HTLC) object;
    return Objects.equals(this.sender, other.sender) &&
        Objects.equals(this.receiverAccount, other.receiverAccount) &&
        Objects.equals(this.holdingCid, other.holdingCid) &&
        Objects.equals(this.hash, other.hash) && Objects.equals(this.unlockTime, other.unlockTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.sender, this.receiverAccount, this.holdingCid, this.hash,
        this.unlockTime);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.synfini.htlc.HTLC(%s, %s, %s, %s, %s)",
        this.sender, this.receiverAccount, this.holdingCid, this.hash, this.unlockTime);
  }

  public static final class ContractId extends com.daml.ledger.javaapi.data.codegen.ContractId<HTLC> implements Exercises<ExerciseCommand> {
    public ContractId(String contractId) {
      super(contractId);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, HTLC, ?> getCompanion(
        ) {
      return COMPANION;
    }

    public static ContractId fromContractId(
        com.daml.ledger.javaapi.data.codegen.ContractId<HTLC> contractId) {
      return COMPANION.toContractId(contractId);
    }
  }

  public static class Contract extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, HTLC> {
    public Contract(ContractId id, HTLC data, Optional<String> agreementText,
        Set<String> signatories, Set<String> observers) {
      super(id, data, agreementText, signatories, observers);
    }

    @Override
    protected ContractCompanion<Contract, ContractId, HTLC> getCompanion() {
      return COMPANION;
    }

    public static Contract fromIdAndRecord(String contractId, DamlRecord record$,
        Optional<String> agreementText, Set<String> signatories, Set<String> observers) {
      return COMPANION.fromIdAndRecord(contractId, record$, agreementText, signatories, observers);
    }

    public static Contract fromCreatedEvent(CreatedEvent event) {
      return COMPANION.fromCreatedEvent(event);
    }
  }

  public interface Exercises<Cmd> extends com.daml.ledger.javaapi.data.codegen.Exercises<Cmd> {
    default Update<Exercised<Transferable.ContractId>> exerciseHTLC_Claim(HTLC_Claim arg) {
      return makeExerciseCmd(CHOICE_HTLC_Claim, arg);
    }

    default Update<Exercised<Transferable.ContractId>> exerciseHTLC_Claim(String sharedHash) {
      return exerciseHTLC_Claim(new HTLC_Claim(sharedHash));
    }

    default Update<Exercised<Transferable.ContractId>> exerciseHTLC_Unlock(HTLC_Unlock arg) {
      return makeExerciseCmd(CHOICE_HTLC_Unlock, arg);
    }

    default Update<Exercised<Transferable.ContractId>> exerciseHTLC_Unlock() {
      return exerciseHTLC_Unlock(new HTLC_Unlock());
    }

    default Update<Exercised<Unit>> exerciseArchive(Archive arg) {
      return makeExerciseCmd(CHOICE_Archive, arg);
    }
  }

  public static final class CreateAnd extends com.daml.ledger.javaapi.data.codegen.CreateAnd implements Exercises<CreateAndExerciseCommand> {
    CreateAnd(Template createArguments) {
      super(createArguments);
    }

    @Override
    protected ContractTypeCompanion<? extends com.daml.ledger.javaapi.data.codegen.Contract<ContractId, ?>, ContractId, HTLC, ?> getCompanion(
        ) {
      return COMPANION;
    }
  }
}
