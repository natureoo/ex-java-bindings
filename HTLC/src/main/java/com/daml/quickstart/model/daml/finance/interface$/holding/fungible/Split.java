package com.daml.quickstart.model.daml.finance.interface$.holding.fungible;

import com.daml.ledger.javaapi.data.DamlCollectors;
import com.daml.ledger.javaapi.data.Numeric;
import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlRecord;
import com.daml.ledger.javaapi.data.codegen.PrimitiveValueDecoders;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Split extends DamlRecord<Split> {
  public static final String _packageId = "95644d5c6ff8c9a433820d694916d86d5e94e1418880b66bf0b3e5103dbc0e09";

  public final List<BigDecimal> amounts;

  public Split(List<BigDecimal> amounts) {
    this.amounts = amounts;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static Split fromValue(Value value$) throws IllegalArgumentException {
    return valueDecoder().decode(value$);
  }

  public static ValueDecoder<Split> valueDecoder() throws IllegalArgumentException {
    return value$ -> {
      Value recordValue$ = value$;
      List<com.daml.ledger.javaapi.data.DamlRecord.Field> fields$ = PrimitiveValueDecoders.recordCheck(1,
          recordValue$);
      List<BigDecimal> amounts = PrimitiveValueDecoders.fromList(PrimitiveValueDecoders.fromNumeric)
          .decode(fields$.get(0).getValue());
      return new Split(amounts);
    } ;
  }

  public com.daml.ledger.javaapi.data.DamlRecord toValue() {
    ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field> fields = new ArrayList<com.daml.ledger.javaapi.data.DamlRecord.Field>(1);
    fields.add(new com.daml.ledger.javaapi.data.DamlRecord.Field("amounts", this.amounts.stream().collect(DamlCollectors.toDamlList(v$0 -> new Numeric(v$0)))));
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
    if (!(object instanceof Split)) {
      return false;
    }
    Split other = (Split) object;
    return Objects.equals(this.amounts, other.amounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.amounts);
  }

  @Override
  public String toString() {
    return String.format("com.daml.quickstart.model.daml.finance.interface$.holding.fungible.Split(%s)",
        this.amounts);
  }
}
