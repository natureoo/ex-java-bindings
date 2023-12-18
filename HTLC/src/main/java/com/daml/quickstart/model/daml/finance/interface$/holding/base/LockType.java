package com.daml.quickstart.model.daml.finance.interface$.holding.base;

import com.daml.ledger.javaapi.data.Value;
import com.daml.ledger.javaapi.data.codegen.DamlEnum;
import com.daml.ledger.javaapi.data.codegen.ValueDecoder;

import java.util.HashMap;
import java.util.Map;

public enum LockType implements DamlEnum<LockType> {
  SEMAPHORE,

  REENTRANT;

  private static final com.daml.ledger.javaapi.data.DamlEnum[] __values$ = {new com.daml.ledger.javaapi.data.DamlEnum("Semaphore"), new com.daml.ledger.javaapi.data.DamlEnum("Reentrant")};

  private static final Map<String, LockType> __enums$ = LockType.__buildEnumsMap$();

  private static final Map<String, LockType> __buildEnumsMap$() {
    Map<String, LockType> m = new HashMap<String, LockType>();
    m.put("Semaphore", SEMAPHORE);
    m.put("Reentrant", REENTRANT);
    return m;
  }

  /**
   * @deprecated since Daml 2.5.0; use {@code valueDecoder} instead
   */
  @Deprecated
  public static final LockType fromValue(Value value$) {
    return valueDecoder().decode(value$);
  }

  public static final ValueDecoder<LockType> valueDecoder() {
    return value$ -> {
      String constructor$ = value$.asEnum().orElseThrow(() -> new IllegalArgumentException("Expected DamlEnum to build an instance of the Enum LockType")).getConstructor();
      if (!LockType.__enums$.containsKey(constructor$)) throw new IllegalArgumentException("Expected a DamlEnum with LockType constructor, found " + constructor$);
      return LockType.__enums$.get(constructor$);
    } ;
  }

  public final com.daml.ledger.javaapi.data.DamlEnum toValue() {
    return LockType.__values$[ordinal()];
  }
}
