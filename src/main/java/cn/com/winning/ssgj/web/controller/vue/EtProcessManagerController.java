package cn.com.winning.ssgj.web.controller.vue;

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
     * @author: Chen,Kuai
     * @Description: 项目流程信息
     * @date: 2018年2月22日16:36:21
     */
    @RequestMapping("/info")
    @ResponseBody
    public Map<String,Object> processManagerInfo(Long c_id, Long pm_id){
        Map<String,Object> result = new HashMap<String,Object>();
        EtProcessManager entity = new EtProcessManager();
        try {
        entity.setCId((long)4);//合同ID
        entity.setPmId((long)15);//项目id
        List<EtProcessManager> processManagerList = super.getFacade().getEtProcessManagerService()
                .getEtProcessManagerList(entity);
        //根据vue特殊化处理 项目经理类型，实施人员类型  0：未完成 1：完成 2：异常 3：实施中
        //判断各种状态 传入对应的值
        List<ProcessModel> pModelList = new ArrayList<ProcessModel>();
        String[] textProcess =new String[]{"","","","","开始","项目接单","完善项目信息","硬件清单准备","入场准备", "确认项目范围",
                "测试环境搭建","实施计划制","项目启动","模拟系统运行","培训客户&考核","安装站点软硬件",
                "检验易用数据","检验基础数据","维护基础数据","维护易用数据","调研流程&配置","开发接口&交付","单据报表&交付",
                "确认流程数量","确认接口数量","确认硬件数量","确认数据报表数量","评估上线可行性","审批切换方案",
                "安排人员到岗","切换项目", "切换项目"};
        Integer isCommon;
        entity =  processManagerList.get(0);
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 4; i <13 ; i++) {
            ProcessModel pModel = new ProcessModel();
            Field f = fields[i];
            f.setAccessible(true);
            isCommon= (Integer) f.get(entity);
            pModel.setState(stateClass(isCommon,1));
            pModel.setText(textProcess[i]);
            pModelList.add(pModel);
        }
        result.put("result",pModelList);


        //第二部分
            List<ProcessModel> pModelList_two = new ArrayList<ProcessModel>();
            ProcessModel pModel2 = new ProcessModel();
            isCommon = entity.getIsSimulation();//系统模拟运行
            pModel2.setState(stateClass(isCommon,2));
            pModel2.setText("模拟系统运行");
            pModelList_two.add(pModel2);

            //节点汇总
            ProcessModel pModel3 = new ProcessModel();
            pModel3.setState("state3");
            String str="";
            for (int i = 13; i <25 ; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                isCommon= (Integer) f.get(entity);
                if(isCommon == 0){
                    str ="state4";
                }
            }
            if(StringUtils.isNotBlank(str)){
                pModel3.setState(str);
            }
            pModel3.setText("");
            pModelList_two.add(pModel3);

            ProcessModel pModel4 = new ProcessModel();
            isCommon = entity.getIsTraining();
            pModel4.setState(stateClass(isCommon,2));
            pModel4.setText("培训客户&考核");
            pModelList_two.add(pModel4);

            ProcessModel pModel5 = new ProcessModel();
            isCommon = entity.getIsTraining();
            pModel5.setState(stateClass(isCommon,2));
            pModel5.setText("安装站点软硬件");
            pModelList_two.add(pModel5);
            //节点汇总
            ProcessModel pModel6 = new ProcessModel();
            pModel6.setState("state3");
            String str6="";
            for (int i = 13; i <22 ; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                isCommon= (Integer) f.get(entity);
                if(isCommon == 0){
                    str6 ="state4";
                }
            }
            if(StringUtils.isNotBlank(str6)){
                pModel6.setState(str6);
            }
            pModel6.setText("");
            pModelList_two.add(pModel6);

            //节点汇总
            ProcessModel pModel7 = new ProcessModel();
            pModel7.setState("state3");
            String str7="";
            for (int i = 13; i <25 ; i++) {
                Field f = fields[i];
                f.setAccessible(true);
                isCommon= (Integer) f.get(entity);
                if(isCommon == 0){
                    str7 = "state4";
                }
            }
            if(StringUtils.isNotBlank(str7)){
                pModel7.setState(str7);
            }
            pModel7.setText("");
            pModelList_two.add(pModel7);

            for (int i = 16; i <27 ; i++) { //当22的时候存在数据计数
                ProcessModel pModel = new ProcessModel();
                if(i==22){
                    ProcessModel pModel22 = new ProcessModel();
                    ProcessModel pModel23 = new ProcessModel();
                    //计算前面四个值是否都以完成
                    int num=1;
                    for (int j = 16; j <20; j++) {
                        Field f = fields[j];
                        f.setAccessible(true);
                        isCommon= (Integer) f.get(entity);
                        if(isCommon==0){
                            num =0;
                        }
                    }
                    Field f = fields[i];
                    f.setAccessible(true);
                    isCommon= (Integer) f.get(entity);
                    pModel.setState(stateClass(isCommon,2));
                    pModel.setText(textProcess[i]);
                    pModelList_two.add(pModel);
                    pModel22.setText("数据维护");
                    pModel22.setState(stateClass(num,2));
                    pModelList_two.add(pModel22);
                }else{
                    Field f = fields[i];
                    f.setAccessible(true);
                    isCommon= (Integer) f.get(entity);
                    pModel.setState(stateClass(isCommon,2));
                    pModel.setText(textProcess[i]);
                    pModelList_two.add(pModel);
                }

            }
            result.put("result_two",pModelList_two);

        //第三阶段 27
            List<ProcessModel> pModelList_Three = new ArrayList<ProcessModel>();

            for (int i = 27; i <31 ; i++) {
                ProcessModel pModel = new ProcessModel();
                Field f = fields[i];
                f.setAccessible(true);
                isCommon= (Integer) f.get(entity);
                pModel.setState(stateClass(isCommon,2));
                pModel.setText(textProcess[i]);
                pModelList_Three.add(pModel);
            }

            ProcessModel pModel31 = new ProcessModel();
            isCommon = processManagerList.get(0).getIsAccept();
            pModel31.setState(stateClass(isCommon,2));
            pModel31.setText("结束");
            pModelList_Three.add(pModel31);
            result.put("result_Three",pModelList_Three);


        } catch (Exception e) {
            e.printStackTrace();
        }
//        isCommon = processManagerList.get(0).getIsStart();
//        pModel.setState(stateClass(isCommon,1));
//        pModel.setText("开始");
//        pModelList.add(pModel);
//        ProcessModel pModel2 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsAccept();
//        pModel2.setState(stateClass(isCommon,1));
//        pModel2.setText("项目接单");
//        pModelList.add(pModel2);
//        ProcessModel pModel3 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsImprove();
//        pModel3.setState(stateClass(isCommon,1));
//        pModel3.setText("完善项目信息");
//        pModelList.add(pModel3);
//        ProcessModel pModel4 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsTestHardware();
//        pModel4.setState(stateClass(isCommon,1));
//        pModel4.setText("硬件清单准备");
//        pModelList.add(pModel4);
//        ProcessModel pModel5 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsPmEntrance();
//        pModel5.setState(stateClass(isCommon,1));
//        pModel5.setText("入场准备");
//        pModelList.add(pModel5);
//        ProcessModel pModel6 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsEtPlan();
//        pModel6.setState(stateClass(isCommon,1));
//        pModel6.setText("实施计划制");
//        pModelList.add(pModel6);
//        ProcessModel pModel7 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsCreateTestEnv();
//        pModel7.setState(stateClass(isCommon,1));
//        pModel7.setText("测试环境搭建");
//        pModelList.add(pModel7);
//        ProcessModel pModel8 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsPmScope();
//        pModel8.setState(stateClass(isCommon,1));
//        pModel8.setText("确认项目范围");
//        pModelList.add(pModel8);
//        ProcessModel pModel9 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsPmStartMeeting();
//        pModel9.setState(stateClass(isCommon,1));
//        pModel9.setText("项目启动");
//        pModelList.add(pModel9);
//
//        /***  实施人员流程信息 line2 *****/
//
//        List<ProcessModel> pModelList_Two = new ArrayList<ProcessModel>();
//        ProcessModel pModel_two = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsBasicDataUse();
//        pModel_two.setState(stateClass(isCommon,2));
//        pModel_two.setText("基础数据维护");
//        pModelList_Two.add(pModel_two);
//        ProcessModel pModel_two2 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsEasyDataUse();
//        pModel_two2.setState(stateClass(isCommon,2));
//        pModel_two2.setText("易用数据维护");
//        pModelList_Two.add(pModel_two2);
//        ProcessModel pModel_two3 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsBasicDataCheck();
//        pModel_two3.setState(stateClass(isCommon,2));
//        pModel_two3.setText("基础数据校验");
//        pModelList_Two.add(pModel_two3);
//        ProcessModel pModel_two4 = new ProcessModel();
//        isCommon = processManagerList.get(0).getIsBasicDataCheck();
//        pModel_two4.setState(stateClass(isCommon,2));
//        pModel_two4.setText("基础数据校验");
//        pModelList_Two.add(pModel_two4);


        return result;

    }

    public String stateClass(int param,int type){
        String stateClass;
        //项目经理类型
        if(type ==1){
            if(param==0){
                stateClass="state2";//未完成
            }else if (param ==1){
                stateClass="state1"; //完成
            }else {
                stateClass="state5"; //异常
            }
        } else{   //项目实施员工类型
            if(param==0){
                stateClass="state4";//未完成
            }else if (param ==1){
                stateClass="state3"; //完成
            }else if(param ==3){
                stateClass="state7";  //正在实施中
            }else {
                stateClass="state6"; //异常
            }
        }
        return stateClass;
    }


}
