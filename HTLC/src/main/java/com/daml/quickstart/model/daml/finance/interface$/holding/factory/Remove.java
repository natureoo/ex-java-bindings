package com.daml.quickstart.model.daml.finance.interface$.holding.factory;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.da.set.types.Set;
import com.daml.quickstart.model.daml.finance.interface$.holding.base.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Remove extends DamlRecord<Remove> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final Set<String> actors;

  public final Base.ContractId holdingCid;

  public Remove(Set<String> actors, Base.ContractId holdingCid) {
    this.actors = actors;
    this.holdingCid = holdingCid;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Remove fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Remove> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(2,
          recordValue$);
      Set<String> actors = Set.<String>valueDecoder(PrimitiveValueDecoders.fromParty)
          .decode(fields$.get(0).getValue());
      Base.ContractId holdingCid =
          new Base.ContractId(fields$.get(1).getValue().asContractId().orElseThrow(() -> new IllegalArgumentException("Expected holdingCid to be of type com.daml.ledger.javaapi.data.ContractId")).getValue());
      return new Remove(actors, holdingCid);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(2);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("actors", this.actors.toValue(v$0 -> new Party(v$0))));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("holdingCid", this.holdingCid.toValue()));
    return new com.daml.ledger.javaapi.data.DamlRecord(fields);
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null) {
      return false;
    }
    if (!(object instanceof Remove)) {
      return false;
    }
    Remove other = (Remove) object;
    return Objects.equals(this.actors, other.actors) &&
        Objects.equals(this.holdingCid, other.holdingCid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.actors, this.holdingCid);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.factory.Remove(%s, %s)",
        this.actors, this.holdingCid);
  }
}
