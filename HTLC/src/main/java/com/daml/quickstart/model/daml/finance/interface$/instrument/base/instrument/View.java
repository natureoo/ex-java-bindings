package com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument;

import com.daml.ledger.javaapi.data.Party;
import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Timestamp;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import com.daml.quickstart.model.daml.finance.interface$.types.common.types.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class View extends DamlRecord<View> {
  public static final String _packageId = "e42b454a2dc8f6726d45e36ee2b59e73d2cac95bded3be60ae3de9ac5a783e66";

  public final String issuer;

  public final String depository;

  public final Id id;

  public final String version;

  public final String description;

  public final Instant validAsOf;

  public View(String issuer, String depository, Id id, String version, String description,
      Instant validAsOf) {
    this.issuer = issuer;
    this.depository = depository;
    this.id = id;
    this.version = version;
    this.description = description;
    this.validAsOf = validAsOf;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static View fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<View> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(6,
          recordValue$);
      String issuer = PrimitiveValueDecoders.fromParty.decode(fields$.get(0).getValue());
      String depository = PrimitiveValueDecoders.fromParty.decode(fields$.get(1).getValue());
      Id id = Id.valueDecoder().decode(fields$.get(2).getValue());
      String version = PrimitiveValueDecoders.fromText.decode(fields$.get(3).getValue());
      String description = PrimitiveValueDecoders.fromText.decode(fields$.get(4).getValue());
      Instant validAsOf = PrimitiveValueDecoders.fromTimestamp.decode(fields$.get(5).getValue());
      return new View(issuer, depository, id, version, description, validAsOf);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(6);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("issuer", new Party(this.issuer)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("depository", new Party(this.depository)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("id", this.id.toValue()));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("version", new Text(this.version)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("description", new Text(this.description)));
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("validAsOf", Timestamp.fromInstant(this.validAsOf)));
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
    if (!(object instanceof View)) {
      return false;
    }
    View other = (View) object;
    return Objects.equals(this.issuer, other.issuer) &&
        Objects.equals(this.depository, other.depository) && Objects.equals(this.id, other.id) &&
        Objects.equals(this.version, other.version) &&
        Objects.equals(this.description, other.description) &&
        Objects.equals(this.validAsOf, other.validAsOf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.issuer, this.depository, this.id, this.version, this.description,
        this.validAsOf);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.instrument.base.instrument.View(%s, %s, %s, %s, %s, %s)",
        this.issuer, this.depository, this.id, this.version, this.description, this.validAsOf);
  }
}
