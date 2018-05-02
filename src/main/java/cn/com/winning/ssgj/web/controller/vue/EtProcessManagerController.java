package cn.com.winning.ssgj.web.controller.vue;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.domain.EtProcessManager;
import cn.com.winning.ssgj.domain.ProcessModel;
import cn.com.winning.ssgj.web.controller.common.BaseController;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目流程管理
 *
 * @author ChenKuai
 * @date 2018-02-22 15:56:49
 **/
@CrossOrigin
@Controller
@RequestMapping("/vue/processManager")
public class EtProcessManagerController extends BaseController {

    /**
     * @author: Chen, Kuai
     * @Description: 项目流程信息
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/info")
    @ResponseBody
    public Map<String, Object> processManagerInfo(Long c_id, Long pm_id) {
        Map<String, Object> result = new HashMap<String, Object>();
        EtProcessManager entity = new EtProcessManager();
        try {
           /* entity.setCId((long) 4);//合同ID
            entity.setPmId((long) 15);//项目id*/
           entity.setCId(c_id);
           entity.setPmId(pm_id);
            List<EtProcessManager> processManagerList = super.getFacade().getEtProcessManagerService()
                    .getEtProcessManagerList(entity);
            //根据vue特殊化处理 项目经理类型，实施人员类型  0：未完成 1：完成 2：异常 3：实施中
            //判断各种状态 传入对应的值
            List<ProcessModel> pModelList = new ArrayList<ProcessModel>();
            String[] textProcess = new String[]{"", "", "", "", "开始", "项目接单", "完善项目信息", "硬件清单准备", "入场准备","实施计划制定",
                    "测试环境搭建","确认项目范围", "项目启动",
                    "模拟系统运行", "培训客户&考核", "安装站点软硬件", "检验易用数据", "检验基础数据", "维护基础数据", "维护易用数据", "调研流程&配置", "开发接口&交付", "单据报表&交付",
                    "确认流程数量", "确认接口数量", "确认数据报表数量", "确认硬件数量",
                    "评估上线可行性", "审批切换方案", "安排人员到岗", "切换项目", "切换项目"};
            Integer isCommon;
            entity = processManagerList.get(0);
            Field[] fields = entity.getClass().getDeclaredFields();
            for (int i = 4; i < 12; i++) {
                ProcessModel pModel = new ProcessModel();
                Field f = fields[i];
                f.setAccessible(true);
                isCommon = (Integer) f.get(entity);
                pModel.setState(stateClass(isCommon, 1));
                pModel.setText(textProcess[i]);
                pModelList.add(pModel);
            }

            ProcessModel pModel_1 = new ProcessModel();
            pModel_1.setState("state1");
            String str_1="";
            for (int i = 5; i < 12; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                isCommon = (Integer) f.get(entity);
                if (isCommon == 0) {
                    str_1 = "state2";
                }
            }
            if (StringUtils.isNotBlank(str_1)) {
                pModel_1.setState(str_1);
            }
            pModel_1.setText(textProcess[12]);
            pModelList.add(pModel_1);
            result.put("result", pModelList);

            //第二部分
            List<ProcessModel> pModelList_two = new ArrayList<ProcessModel>();
            ProcessModel pModel2 = new ProcessModel();
            isCommon = entity.getIsSimulation();//系统模拟运行
            pModel2.setState(stateClass(isCommon, 2));
            pModel2.setText("模拟系统运行");
            pModelList_two.add(pModel2);

            //节点汇总
            ProcessModel pModel3 = new ProcessModel();
            pModel3.setState("state3");
            String str = "";
            for (int i = 13; i < 25; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                isCommon = (Integer) f.get(entity);
                if (isCommon == 0) {
                    str = "state4";
                }
            }
            if (StringUtils.isNotBlank(str)) {
                pModel3.setState(str);
            }
            pModel3.setText("");
            pModelList_two.add(pModel3);

            ProcessModel pModel4 = new ProcessModel();
            isCommon = entity.getIsTraining();
            pModel4.setState(stateClass(isCommon, 2));
            pModel4.setText("培训客户&考核");
            pModelList_two.add(pModel4);

            ProcessModel pModel5 = new ProcessModel();
            isCommon = entity.getIsSiteInstall();
            pModel5.setState(stateClass(isCommon, 2));
            pModel5.setText("安装站点软硬件");
            pModelList_two.add(pModel5);
            //节点汇总
            ProcessModel pModel6 = new ProcessModel();
            pModel6.setState("state3");
            String str6 = "";
            for (int i = 15; i < 25; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                isCommon = (Integer) f.get(entity);
                if (isCommon == 0) {
                    str6 = "state4";
                }
            }
            if (StringUtils.isNotBlank(str6)) {
                pModel6.setState(str6);
            }
            pModel6.setText("");
            pModelList_two.add(pModel6);

            //节点汇总
            ProcessModel pModel7 = new ProcessModel();
            pModel7.setState("state3");
            String str7 = "";
            for (int i = 15; i < 19; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                isCommon = (Integer) f.get(entity);
                if (isCommon == 0) {
                    str7 = "state4";
                }
            }
            if (StringUtils.isNotBlank(str7)) {
                pModel7.setState(str7);
            }
            pModel7.setText("");
            pModelList_two.add(pModel7);

            for (int i = 16; i < 27; i++) { //当22的时候存在数据计数
                ProcessModel pModel = new ProcessModel();
                if (i == 22) {
                    ProcessModel pModel22 = new ProcessModel();
                    ProcessModel pModel23 = new ProcessModel();
                    //计算前面四个值是否都以完成
                    int num = 1;
                    for (int j = 16; j < 20; j++) {
                        Field f = fields[j];
                        f.setAccessible(true);
                        isCommon = (Integer) f.get(entity);
                        if (isCommon == 0) {
                            num = 0;
                        }
                    }
                    Field f = fields[i];
                    f.setAccessible(true);
                    isCommon = (Integer) f.get(entity);
                    pModel.setState(stateClass(isCommon, 2));
                    pModel.setText(textProcess[i]);
                    pModelList_two.add(pModel);
                    pModel22.setText("数据维护");
                    pModel22.setState(stateClass(num, 2));
                    pModelList_two.add(pModel22);
                } else {
                    Field f = fields[i];
                    f.setAccessible(true);
                    isCommon = (Integer) f.get(entity);
                    pModel.setState(stateClass(isCommon, 2));
                    pModel.setText(textProcess[i]);
                    pModelList_two.add(pModel);
                }

            }
            result.put("result_two", pModelList_two);

            //第三阶段 27
            List<ProcessModel> pModelList_Three = new ArrayList<ProcessModel>();

            for (int i = 27; i < 31; i++) {
                ProcessModel pModel = new ProcessModel();
                Field f = fields[i];
                f.setAccessible(true);
                isCommon = (Integer) f.get(entity);
                pModel.setState(stateClass(isCommon, 2));
                pModel.setText(textProcess[i]);
                pModelList_Three.add(pModel);
            }

            ProcessModel pModel31 = new ProcessModel();
            isCommon = processManagerList.get(0).getIsAccept();
            pModel31.setState(stateClass(isCommon, 2));
            pModel31.setText("结束");
            pModelList_Three.add(pModel31);
            result.put("result_Three", pModelList_Three);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    public String stateClass(int param, int type) {
        String stateClass;
        //项目经理类型
        if (type == 1) {
            if (param == 0) {
                stateClass = "state2";//未完成
            } else if (param == 1) {
                stateClass = "state1"; //完成
            } else {
                stateClass = "state5"; //异常
            }
        } else {   //项目实施员工类型
            if (param == 0) {
                stateClass = "state4";//未完成
            } else if (param == 1) {
                stateClass = "state3"; //完成
            } else if (param == 3) {
                stateClass = "state7";  //正在实施中
            } else {
                stateClass = "state6"; //异常
            }
        }
        return stateClass;
    }


}
