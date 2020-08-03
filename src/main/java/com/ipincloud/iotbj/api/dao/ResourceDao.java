package com.ipincloud.iotbj.api.dao;

import com.alibaba.fastjson.JSONObject;
import com.ipincloud.iotbj.api.domain.AuditLog;
import com.ipincloud.iotbj.srv.domain.User;
import org.apache.ibatis.annotations.Param;

public interface ResourceDao {

    int insertOrUpdateOrg(@Param("info") JSONObject info);

    int updateOrgOther();

    int insertOrUpdateRegion(@Param("info") JSONObject info);

    int updateResionOther();

    int insertOrUpdateUser(@Param("info") JSONObject info);

    int exitsUserbyPersonId(String personId);

    User getAccountByCertificateNum(String mobiles);

    int saveAuditLog(AuditLog auditLog);

    int insertOrUpdateVehicleHistory(@Param("jsonObj") JSONObject jsonObj);

    String queryGateTitle(@Param("gateIndex") String gateIndex);
}
