package com.daml.quickstart.model.synfini.htlc;

import com.daml.ledger.javaapi.data.Text;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;
import java.lang.Deprecated;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HTLC_Claim extends DamlRecord<HTLC_Claim> {
  public static final String _packageId = "ed470a2759bcfa1304b57e1221c2f15bfe11e4b573ad92d640a73ccc1dccbda5";

  public final String sharedHash;

  public HTLC_Claim(String sharedHash) {
    this.sharedHash = sharedHash;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static HTLC_Claim fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<HTLC_Claim> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      String sharedHash = PrimitiveValueDecoders.fromText.decode(fields$.get(0).getValue());
      return new HTLC_Claim(sharedHash);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("sharedHash", new Text(this.sharedHash)));
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
    if (!(object instanceof HTLC_Claim)) {
      return false;
    }
    HTLC_Claim other = (HTLC_Claim) object;
    return Objects.equals(this.sharedHash, other.sharedHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.sharedHash);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.synfini.htlc.HTLC_Claim(%s)", this.sharedHash);
  }
}
