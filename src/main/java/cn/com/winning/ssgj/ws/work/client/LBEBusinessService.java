package cn.com.winning.ssgj.ws.work.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.3
 * Wed May 09 21:25:16 CST 2018
 * Generated source version: 2.2.3
 * 
 */
 
@WebService(targetNamespace = "http://ws.livebos.apex.com/", name = "LBEBusinessService")
@XmlSeeAlso({ObjectFactory.class})
public interface LBEBusinessService {

    @WebMethod
    @RequestWrapper(localName = "queryTasks", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryTasks")
    @ResponseWrapper(localName = "queryTasksResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryTasksResponse")
    @WebResult(name = "QueryResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.QueryResult queryTasks(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "caller", targetNamespace = "")
        java.lang.String caller,
        @WebParam(name = "queryOption", targetNamespace = "")
        cn.com.winning.ssgj.ws.work.client.QueryOption queryOption
    );

    @WebMethod
    @RequestWrapper(localName = "getNotice", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetNotice")
    @ResponseWrapper(localName = "getNoticeResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetNoticeResponse")
    @WebResult(name = "NoticeResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.NoticeResult getNotice(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "user", targetNamespace = "")
        java.lang.String user,
        @WebParam(name = "type", targetNamespace = "")
        int type
    );

    @WebMethod
    @RequestWrapper(localName = "logout", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.Logout")
    @ResponseWrapper(localName = "logoutResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.LogoutResponse")
    @WebResult(name = "LogoutResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.LogoutResult logout(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId
    );

    @WebMethod
    @RequestWrapper(localName = "validateUser", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ValidateUser")
    @ResponseWrapper(localName = "validateUserResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ValidateUserResponse")
    @WebResult(name = "UserInfo", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.QueryResult validateUser(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "userId", targetNamespace = "")
        java.lang.String userId,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "algorithm", targetNamespace = "")
        java.lang.String algorithm,
        @WebParam(name = "securityCode", targetNamespace = "")
        java.lang.String securityCode
    );

    @WebMethod
    @RequestWrapper(localName = "queryWorkStepDetail", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryWorkStepDetail")
    @ResponseWrapper(localName = "queryWorkStepDetailResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryWorkStepDetailResponse")
    @WebResult(name = "WorkStepDetail", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.QueryResult queryWorkStepDetail(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "instanceId", targetNamespace = "")
        long instanceId,
        @WebParam(name = "queryOption", targetNamespace = "")
        cn.com.winning.ssgj.ws.work.client.QueryOption queryOption
    );

    @WebMethod
    @RequestWrapper(localName = "queryWorkflowDescribe", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryWorkflowDescribe")
    @ResponseWrapper(localName = "queryWorkflowDescribeResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryWorkflowDescribeResponse")
    @WebResult(name = "WorkflowDescirbe", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.WorkflowDescribeResponse queryWorkflowDescribe(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "uid", targetNamespace = "")
        java.lang.String uid,
        @WebParam(name = "startupOnly", targetNamespace = "")
        boolean startupOnly
    );

    @WebMethod
    @RequestWrapper(localName = "queryTaskListByCondition", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryTaskListByCondition")
    @ResponseWrapper(localName = "queryTaskListByConditionResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryTaskListByConditionResponse")
    @WebResult(name = "QueryResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.QueryResult queryTaskListByCondition(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "caller", targetNamespace = "")
        java.lang.String caller,
        @WebParam(name = "condition", targetNamespace = "")
        cn.com.winning.ssgj.ws.work.client.WorkCondition condition,
        @WebParam(name = "queryOption", targetNamespace = "")
        cn.com.winning.ssgj.ws.work.client.QueryOption queryOption
    );

    @WebMethod
    @RequestWrapper(localName = "callBizFunction", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.CallBizFunction")
    @ResponseWrapper(localName = "callBizFunctionResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.CallBizFunctionResponse")
    @WebResult(name = "BizFunctionResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.BizFunctionResult callBizFunction(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "bizFunctionName", targetNamespace = "")
        java.lang.String bizFunctionName,
        @WebParam(name = "params", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> params
    );

    @WebMethod
    @RequestWrapper(localName = "doWorkAction", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.DoWorkAction")
    @ResponseWrapper(localName = "doWorkActionResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.DoWorkActionResponse")
    @WebResult(name = "WorkActionResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.WorkActionResult doWorkAction(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "workflowName", targetNamespace = "")
        java.lang.String workflowName,
        @WebParam(name = "instanceId", targetNamespace = "")
        long instanceId,
        @WebParam(name = "actionId", targetNamespace = "")
        int actionId,
        @WebParam(name = "params", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> params,
        @WebParam(name = "caller", targetNamespace = "")
        java.lang.String caller,
        @WebParam(name = "summary", targetNamespace = "")
        java.lang.String summary
    );

    @WebMethod
    @RequestWrapper(localName = "create", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.CreateResponse")
    @WebResult(name = "CreateResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.CreateResult create(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "objectName", targetNamespace = "")
        java.lang.String objectName,
        @WebParam(name = "params", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> params
    );

    @WebMethod
    @RequestWrapper(localName = "getUserInfo", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetUserInfo")
    @ResponseWrapper(localName = "getUserInfoResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetUserInfoResponse")
    @WebResult(name = "UserInfo", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.UserInfo getUserInfo(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "loginId", targetNamespace = "")
        java.lang.String loginId
    );

    @WebMethod
    @RequestWrapper(localName = "getWorkOwner", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetWorkOwner")
    @ResponseWrapper(localName = "getWorkOwnerResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetWorkOwnerResponse")
    @WebResult(name = "WorkOwnerResponse", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.WorkOwnerResponse getWorkOwner(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "instanceId", targetNamespace = "")
        long instanceId,
        @WebParam(name = "stepId", targetNamespace = "")
        int stepId
    );

    @WebMethod
    @RequestWrapper(localName = "doWorkActionByObject", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.DoWorkActionByObject")
    @ResponseWrapper(localName = "doWorkActionByObjectResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.DoWorkActionByObjectResponse")
    @WebResult(name = "WorkActionResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.WorkActionResult doWorkActionByObject(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "objectName", targetNamespace = "")
        java.lang.String objectName,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "actionId", targetNamespace = "")
        int actionId,
        @WebParam(name = "params", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> params,
        @WebParam(name = "caller", targetNamespace = "")
        java.lang.String caller,
        @WebParam(name = "summary", targetNamespace = "")
        java.lang.String summary
    );

    @WebMethod
    @RequestWrapper(localName = "killWorkflow", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.KillWorkflow")
    @ResponseWrapper(localName = "killWorkflowResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.KillWorkflowResponse")
    @WebResult(name = "LBEResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.LbeResult killWorkflow(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "workflowName", targetNamespace = "")
        java.lang.String workflowName,
        @WebParam(name = "caller", targetNamespace = "")
        java.lang.String caller,
        @WebParam(name = "instanceId", targetNamespace = "")
        long instanceId,
        @WebParam(name = "reason", targetNamespace = "")
        java.lang.String reason
    );

    @WebMethod
    @RequestWrapper(localName = "validateSessionId", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ValidateSessionId")
    @ResponseWrapper(localName = "validateSessionIdResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ValidateSessionIdResponse")
    @WebResult(name = "LBEResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.LbeResult validateSessionId(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId
    );

    @WebMethod
    @RequestWrapper(localName = "execBizProcess", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ExecBizProcess")
    @ResponseWrapper(localName = "execBizProcessResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ExecBizProcessResponse")
    @WebResult(name = "BizProcessResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.BizProcessResult execBizProcess(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "bizProcessName", targetNamespace = "")
        java.lang.String bizProcessName,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "params", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> params,
        @WebParam(name = "variables", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> variables
    );

    @WebMethod
    @RequestWrapper(localName = "getWorkAvailableAction", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetWorkAvailableAction")
    @ResponseWrapper(localName = "getWorkAvailableActionResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.GetWorkAvailableActionResponse")
    @WebResult(name = "AvailableWorkActionResponse", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.AvailableWorkActionResponse getWorkAvailableAction(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "instanceId", targetNamespace = "")
        long instanceId
    );

    @WebMethod
    @RequestWrapper(localName = "query", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.Query")
    @ResponseWrapper(localName = "queryResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.QueryResponse")
    @WebResult(name = "QueryResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.QueryResult query(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "objectName", targetNamespace = "")
        java.lang.String objectName,
        @WebParam(name = "params", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> params,
        @WebParam(name = "condition", targetNamespace = "")
        java.lang.String condition,
        @WebParam(name = "queryOption", targetNamespace = "")
        cn.com.winning.ssgj.ws.work.client.QueryOption queryOption
    );

    @WebMethod
    @RequestWrapper(localName = "update", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.UpdateResponse")
    @WebResult(name = "LBEResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.LbeResult update(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "objectName", targetNamespace = "")
        java.lang.String objectName,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id,
        @WebParam(name = "params", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> params
    );

    @WebMethod
    @RequestWrapper(localName = "execBizService", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ExecBizService")
    @ResponseWrapper(localName = "execBizServiceResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.ExecBizServiceResponse")
    @WebResult(name = "BizServiceResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.BizProcessResult execBizService(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "bizServiceName", targetNamespace = "")
        java.lang.String bizServiceName,
        @WebParam(name = "variables", targetNamespace = "")
        java.util.List<cn.com.winning.ssgj.ws.work.client.LbParameter> variables
    );

    @WebMethod
    @RequestWrapper(localName = "login", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.Login")
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.LoginResponse")
    @WebResult(name = "LoginResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.LoginResult login(
        @WebParam(name = "userid", targetNamespace = "")
        java.lang.String userid,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "scheme", targetNamespace = "")
        java.lang.String scheme,
        @WebParam(name = "algorithm", targetNamespace = "")
        java.lang.String algorithm,
        @WebParam(name = "securityCode", targetNamespace = "")
        java.lang.String securityCode
    );

    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://ws.livebos.apex.com/", className = "cn.com.winning.ssgj.ws.work.client.DeleteResponse")
    @WebResult(name = "LBEResult", targetNamespace = "")
    public cn.com.winning.ssgj.ws.work.client.LbeResult delete(
        @WebParam(name = "sessionId", targetNamespace = "")
        java.lang.String sessionId,
        @WebParam(name = "objectName", targetNamespace = "")
        java.lang.String objectName,
        @WebParam(name = "id", targetNamespace = "")
        java.lang.String id
    );
}
