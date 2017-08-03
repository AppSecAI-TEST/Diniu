package com.workapp.auto.carterminal.module.main.bean;

import com.workapp.auto.carterminal.base.BaseResponse;

/**服务端传回来的车辆信息状态
 * Created by Administrator on 2017/8/3 0003.
 */

public class CarInfoCheckReturnBean extends BaseResponse{

    /**
     * data : {"insuranceCertificate":"1","insuranceCard":"1","warrantyCard":"1","carTravelLicense":"1","beforePlate":"1","endPlate":"1","frontWheel":"1","rearWheel":"1","windshield":"1","windowGlass":"1","windscreenWiper":"1","retroreflector":"1","doorHandle":"1","carLights":"1","turnLight":"1","backupLight":"0","chargeJack":"1","windowLifterSwitch":"1","lightsSwitch":"1","turnLightSwitch":"1","windscreenWiperSwitch":"1","handBrake":"1","footBrake":"1","accelerator":"1","shifter":"1","sound":"1","horn":"1","steeringWheel":"1","bodyLight":"1","dashBoard":"1","automotiveMedia":"1","airCondition":"1","rearviewMirror":"1","sunvisor":"1","safetyBelt":"1","seat":"1","jack":"1","kit":"1","faultWarningBoard":"1","spareWheel":"1","fireExtinguisher":"1","carKey":"1","accessoryManual":"1","carHeadPhoto":"1","carEndPhoto":"1","leftCarBodyPhoto":"1","rightCarBodyPhoto":"1","videoUrl":"1"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * insuranceCertificate : 1
         * insuranceCard : 1
         * warrantyCard : 1
         * carTravelLicense : 1
         * beforePlate : 1
         * endPlate : 1
         * frontWheel : 1
         * rearWheel : 1
         * windshield : 1
         * windowGlass : 1
         * windscreenWiper : 1
         * retroreflector : 1
         * doorHandle : 1
         * carLights : 1
         * turnLight : 1
         * backupLight : 0
         * chargeJack : 1
         * windowLifterSwitch : 1
         * lightsSwitch : 1
         * turnLightSwitch : 1
         * windscreenWiperSwitch : 1
         * handBrake : 1
         * footBrake : 1
         * accelerator : 1
         * shifter : 1
         * sound : 1
         * horn : 1
         * steeringWheel : 1
         * bodyLight : 1
         * dashBoard : 1
         * automotiveMedia : 1
         * airCondition : 1
         * rearviewMirror : 1
         * sunvisor : 1
         * safetyBelt : 1
         * seat : 1
         * jack : 1
         * kit : 1
         * faultWarningBoard : 1
         * spareWheel : 1
         * fireExtinguisher : 1
         * carKey : 1
         * accessoryManual : 1
         * carHeadPhoto : 1
         * carEndPhoto : 1
         * leftCarBodyPhoto : 1
         * rightCarBodyPhoto : 1
         * videoUrl : 1
         */

        private String insuranceCertificate;
        private String insuranceCard;
        private String warrantyCard;
        private String carTravelLicense;
        private String beforePlate;
        private String endPlate;
        private String frontWheel;
        private String rearWheel;
        private String windshield;
        private String windowGlass;
        private String windscreenWiper;
        private String retroreflector;
        private String doorHandle;
        private String carLights;
        private String turnLight;
        private String backupLight;
        private String chargeJack;
        private String windowLifterSwitch;
        private String lightsSwitch;
        private String turnLightSwitch;
        private String windscreenWiperSwitch;
        private String handBrake;
        private String footBrake;
        private String accelerator;
        private String shifter;
        private String sound;
        private String horn;
        private String steeringWheel;
        private String bodyLight;
        private String dashBoard;
        private String automotiveMedia;
        private String airCondition;
        private String rearviewMirror;
        private String sunvisor;
        private String safetyBelt;
        private String seat;
        private String jack;
        private String kit;
        private String faultWarningBoard;
        private String spareWheel;
        private String fireExtinguisher;
        private String carKey;
        private String accessoryManual;
        private String carHeadPhoto;
        private String carEndPhoto;
        private String leftCarBodyPhoto;
        private String rightCarBodyPhoto;
        private String videoUrl;

        public String getInsuranceCertificate() {
            return insuranceCertificate;
        }

        public void setInsuranceCertificate(String insuranceCertificate) {
            this.insuranceCertificate = insuranceCertificate;
        }

        public String getInsuranceCard() {
            return insuranceCard;
        }

        public void setInsuranceCard(String insuranceCard) {
            this.insuranceCard = insuranceCard;
        }

        public String getWarrantyCard() {
            return warrantyCard;
        }

        public void setWarrantyCard(String warrantyCard) {
            this.warrantyCard = warrantyCard;
        }

        public String getCarTravelLicense() {
            return carTravelLicense;
        }

        public void setCarTravelLicense(String carTravelLicense) {
            this.carTravelLicense = carTravelLicense;
        }

        public String getBeforePlate() {
            return beforePlate;
        }

        public void setBeforePlate(String beforePlate) {
            this.beforePlate = beforePlate;
        }

        public String getEndPlate() {
            return endPlate;
        }

        public void setEndPlate(String endPlate) {
            this.endPlate = endPlate;
        }

        public String getFrontWheel() {
            return frontWheel;
        }

        public void setFrontWheel(String frontWheel) {
            this.frontWheel = frontWheel;
        }

        public String getRearWheel() {
            return rearWheel;
        }

        public void setRearWheel(String rearWheel) {
            this.rearWheel = rearWheel;
        }

        public String getWindshield() {
            return windshield;
        }

        public void setWindshield(String windshield) {
            this.windshield = windshield;
        }

        public String getWindowGlass() {
            return windowGlass;
        }

        public void setWindowGlass(String windowGlass) {
            this.windowGlass = windowGlass;
        }

        public String getWindscreenWiper() {
            return windscreenWiper;
        }

        public void setWindscreenWiper(String windscreenWiper) {
            this.windscreenWiper = windscreenWiper;
        }

        public String getRetroreflector() {
            return retroreflector;
        }

        public void setRetroreflector(String retroreflector) {
            this.retroreflector = retroreflector;
        }

        public String getDoorHandle() {
            return doorHandle;
        }

        public void setDoorHandle(String doorHandle) {
            this.doorHandle = doorHandle;
        }

        public String getCarLights() {
            return carLights;
        }

        public void setCarLights(String carLights) {
            this.carLights = carLights;
        }

        public String getTurnLight() {
            return turnLight;
        }

        public void setTurnLight(String turnLight) {
            this.turnLight = turnLight;
        }

        public String getBackupLight() {
            return backupLight;
        }

        public void setBackupLight(String backupLight) {
            this.backupLight = backupLight;
        }

        public String getChargeJack() {
            return chargeJack;
        }

        public void setChargeJack(String chargeJack) {
            this.chargeJack = chargeJack;
        }

        public String getWindowLifterSwitch() {
            return windowLifterSwitch;
        }

        public void setWindowLifterSwitch(String windowLifterSwitch) {
            this.windowLifterSwitch = windowLifterSwitch;
        }

        public String getLightsSwitch() {
            return lightsSwitch;
        }

        public void setLightsSwitch(String lightsSwitch) {
            this.lightsSwitch = lightsSwitch;
        }

        public String getTurnLightSwitch() {
            return turnLightSwitch;
        }

        public void setTurnLightSwitch(String turnLightSwitch) {
            this.turnLightSwitch = turnLightSwitch;
        }

        public String getWindscreenWiperSwitch() {
            return windscreenWiperSwitch;
        }

        public void setWindscreenWiperSwitch(String windscreenWiperSwitch) {
            this.windscreenWiperSwitch = windscreenWiperSwitch;
        }

        public String getHandBrake() {
            return handBrake;
        }

        public void setHandBrake(String handBrake) {
            this.handBrake = handBrake;
        }

        public String getFootBrake() {
            return footBrake;
        }

        public void setFootBrake(String footBrake) {
            this.footBrake = footBrake;
        }

        public String getAccelerator() {
            return accelerator;
        }

        public void setAccelerator(String accelerator) {
            this.accelerator = accelerator;
        }

        public String getShifter() {
            return shifter;
        }

        public void setShifter(String shifter) {
            this.shifter = shifter;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public String getHorn() {
            return horn;
        }

        public void setHorn(String horn) {
            this.horn = horn;
        }

        public String getSteeringWheel() {
            return steeringWheel;
        }

        public void setSteeringWheel(String steeringWheel) {
            this.steeringWheel = steeringWheel;
        }

        public String getBodyLight() {
            return bodyLight;
        }

        public void setBodyLight(String bodyLight) {
            this.bodyLight = bodyLight;
        }

        public String getDashBoard() {
            return dashBoard;
        }

        public void setDashBoard(String dashBoard) {
            this.dashBoard = dashBoard;
        }

        public String getAutomotiveMedia() {
            return automotiveMedia;
        }

        public void setAutomotiveMedia(String automotiveMedia) {
            this.automotiveMedia = automotiveMedia;
        }

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public String getRearviewMirror() {
            return rearviewMirror;
        }

        public void setRearviewMirror(String rearviewMirror) {
            this.rearviewMirror = rearviewMirror;
        }

        public String getSunvisor() {
            return sunvisor;
        }

        public void setSunvisor(String sunvisor) {
            this.sunvisor = sunvisor;
        }

        public String getSafetyBelt() {
            return safetyBelt;
        }

        public void setSafetyBelt(String safetyBelt) {
            this.safetyBelt = safetyBelt;
        }

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public String getJack() {
            return jack;
        }

        public void setJack(String jack) {
            this.jack = jack;
        }

        public String getKit() {
            return kit;
        }

        public void setKit(String kit) {
            this.kit = kit;
        }

        public String getFaultWarningBoard() {
            return faultWarningBoard;
        }

        public void setFaultWarningBoard(String faultWarningBoard) {
            this.faultWarningBoard = faultWarningBoard;
        }

        public String getSpareWheel() {
            return spareWheel;
        }

        public void setSpareWheel(String spareWheel) {
            this.spareWheel = spareWheel;
        }

        public String getFireExtinguisher() {
            return fireExtinguisher;
        }

        public void setFireExtinguisher(String fireExtinguisher) {
            this.fireExtinguisher = fireExtinguisher;
        }

        public String getCarKey() {
            return carKey;
        }

        public void setCarKey(String carKey) {
            this.carKey = carKey;
        }

        public String getAccessoryManual() {
            return accessoryManual;
        }

        public void setAccessoryManual(String accessoryManual) {
            this.accessoryManual = accessoryManual;
        }

        public String getCarHeadPhoto() {
            return carHeadPhoto;
        }

        public void setCarHeadPhoto(String carHeadPhoto) {
            this.carHeadPhoto = carHeadPhoto;
        }

        public String getCarEndPhoto() {
            return carEndPhoto;
        }

        public void setCarEndPhoto(String carEndPhoto) {
            this.carEndPhoto = carEndPhoto;
        }

        public String getLeftCarBodyPhoto() {
            return leftCarBodyPhoto;
        }

        public void setLeftCarBodyPhoto(String leftCarBodyPhoto) {
            this.leftCarBodyPhoto = leftCarBodyPhoto;
        }

        public String getRightCarBodyPhoto() {
            return rightCarBodyPhoto;
        }

        public void setRightCarBodyPhoto(String rightCarBodyPhoto) {
            this.rightCarBodyPhoto = rightCarBodyPhoto;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }
    }
}
