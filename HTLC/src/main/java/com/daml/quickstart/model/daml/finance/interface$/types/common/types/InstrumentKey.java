package com.daml.quickstart.model.daml.finance.interface$.types.common.types;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InstrumentKey extends DamlRecord<InstrumentKey> {
  public static final String _packageId = "361a5742b581d2d0dc1378f905a7f0ff692d3eb0d79b9fde51e34390813cf7d4";

  public final String depository;

  public final String issuer;

  public final Id id;

  public final String version;

  public InstrumentKey(String depository, String issuer, Id id, String version) {
    this.depository = depository;
    this.issuer = issuer;
    this.id = id;
    this.version = version;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static InstrumentKey fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<InstrumentKey> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(4,
          recordValue$);
      String depository = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String issuer = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(2).getValue());
      String version = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      return new InstrumentKey(depository, issuer, id, version);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(4);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("depository", new Party(this.depository)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("issuer", new Party(this.issuer)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("version", new Text(this.version)));
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
    if (!(object instanceof InstrumentKey)) {
      return false;
    }
    InstrumentKey other = (InstrumentKey) object;
    return Objects.equals(this.depository, other.depository) &&
        Objects.equals(this.issuer, other.issuer) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.version, other.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.depository, this.issuer, this.id, this.version);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.types.common.types.InstrumentKey(%s, %s, %s, %s)",
        this.depository, this.issuer, this.id, this.version);
  }
}
