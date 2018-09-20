package com.lingnet.vocs.action.monit;

public enum AdsZCRVarName {
    AdsInletConc1("X0_手动自动", 12),
    AdsInletConc2("X1_急停", 13),
    AdsInletConc3("X2_压缩空气开关", 14),
    AdsInletConc4("X3_转轮脱附出口切断阀MV1102开", 15),
    AdsInletConc5("X4_转轮脱附出口切断阀MV1102关", 16),
    AdsInletConc6("X5_炉膛循环升温阀MV1103开", 17),
    AdsInletConc7("X6_炉膛循环升温阀MV1103关", 18),
    AdsInletConc8("X7_蓄热换热器切断阀MV1104开", 19),
    AdsInletConc9("X10_蓄热换热器切断阀MV1104关", 20),
    AdsInletConc10("X11_转轮新风控制阀YV1102开", 21),
    AdsInletConc11("X12_转轮新风控制阀YV1102关", 22),
    AdsInletConc12("X13_转轮入口控制阀YV1101开", 23),
    AdsInletConc13("X14_转轮入口控制阀YV1101关", 24),
    AdsInletConc14("转轮检测开关", 25),
    AdsInletConc15("2", 26),
    AdsInletConc16("23", 27),
    AdsInletConc17("234", 28),
    AdsInletConc18("X21_远程启动", 29),
    AdsInletConc19("X22_远程停止", 30),
    AdsInletConc20("压差开关1", 31),
    AdsInletConc21("压差开关2", 32),
    AdsInletConc22("压差开关3", 33),
    AdsInletConc23("Y0_转轮脱附出口切断阀MV1102开", 34),
    AdsInletConc24("Y1_转轮脱附出口切断阀MV1102关", 35),
    AdsInletConc25("Y2_炉膛循环升温阀MV1103开", 36),
    AdsInletConc26("Y3_炉膛循环升温阀MV1103关", 37),
    AdsInletConc27("Y4_蓄热换热器切断阀MV1104开", 38),
    AdsInletConc28("Y5_蓄热换热器切断阀MV1104关", 39),
    AdsInletConc29("Y6_转轮新风控制阀YV1102开", 40),
    AdsInletConc30("Y7_转轮新风控制阀YV1102关", 41),
    AdsInletConc31("Y10_转轮入口控制阀YV1101开", 42),
    AdsInletConc32("Y11_转轮入口控制阀YV1101关", 43),
    AdsInletConc33("Y12_允许进废气", 44),
    AdsInletConc34("Y13_连锁故障", 45),
    AdsInletConc35("Y14_运行指示灯", 46),
    AdsInletConc36("Y15_停止指示灯", 47),
    AdsInletConc37("Y16_故障指示灯", 48),
    AdsInletConc38("Y17_蜂鸣器", 49),
    AdsInletConc39("PLC_系统状态字", 50),
    AdsInletConc40("吸附风机频率", 51),
    AdsInletConc41("CO风机频率", 52),
    AdsInletConc42("转轮电机频率", 53),
    AdsInletConc43("蓄热转轮频率", 54),
    AdsInletConc44("TI1101转轮吸附出口温度", 55),
    AdsInletConc45("TI1102转轮冷却出口温度", 56),
    AdsInletConc46("TI1103脱附入口温度", 57),
    AdsInletConc47("TI1104转轮脱附出口温度", 58),
    AdsInletConc48("TI1105电加热后温度", 59),
    AdsInletConc49("TI1107催化床后温度", 60),
    AdsInletConc50("TI1108催化床前温度", 61),
    AdsInletConc51("TI1109  CO换热后温度", 62),
    AdsInletConc52("TI1110  CO进废气温度", 63),
    AdsInletConc53("TI1111  蓄热转轮换热后温度", 64),
    AdsInletConc54("TI1112  表冷器后温度", 65),
    AdsInletConc55("P1101 转轮前压力反馈", 66),
    AdsInletConc56("P1101 CO风机前压力反馈", 67),
    AdsInletConc57("P1103 炉膛压力反馈", 68),
    AdsInletConc58("脱附区压力反馈", 69),
    AdsInletConc59("MV1101 开度反馈", 70),
    AdsInletConc60("MV1106 开度反馈", 71),
    AdsInletConc61("吸附区压力反馈", 72);


    private String name;
    private int index;

    private AdsZCRVarName(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
