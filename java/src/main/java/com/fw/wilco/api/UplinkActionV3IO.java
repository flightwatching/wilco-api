package com.fw.wilco.api;

import java.util.HashMap;
import java.util.Map;

public class UplinkActionV3IO {

    private Long id;
    private String dueDate;
    private String completedDate;
    private String requestDate;
    private String giveUpDate;
    private LayoutV3IO layout;
    private FwotV3IO fwot;
    private String tieback;
    private String recipient;
    private String triggerKind;
    private Long triggerId;
    private String triggerUser;
    private EventV3IO origMsg;
    private EventV3IO ackMsg;
    private EventV3IO rejected;
    private EventV3IO answer;
    private Map<String,String> opts = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getGiveUpDate() {
        return giveUpDate;
    }

    public void setGiveUpDate(String giveUpDate) {
        this.giveUpDate = giveUpDate;
    }

    public LayoutV3IO getLayout() {
        return layout;
    }

    public void setLayout(LayoutV3IO layout) {
        this.layout = layout;
    }

    public FwotV3IO getFwot() {
        return fwot;
    }

    public void setFwot(FwotV3IO fwot) {
        this.fwot = fwot;
    }

    public String getTieback() {
        return tieback;
    }

    public void setTieback(String tieback) {
        this.tieback = tieback;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTriggerKind() {
        return triggerKind;
    }

    public void setTriggerKind(String triggerKind) {
        this.triggerKind = triggerKind;
    }

    public Long getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(Long triggerId) {
        this.triggerId = triggerId;
    }

    public String getTriggerUser() {
        return triggerUser;
    }

    public void setTriggerUser(String triggerUser) {
        this.triggerUser = triggerUser;
    }

    public EventV3IO getOrigMsg() {
        return origMsg;
    }

    public void setOrigMsg(EventV3IO origMsg) {
        this.origMsg = origMsg;
    }

    public EventV3IO getAckMsg() {
        return ackMsg;
    }

    public void setAckMsg(EventV3IO ackMsg) {
        this.ackMsg = ackMsg;
    }

    public EventV3IO getRejected() {
        return rejected;
    }

    public void setRejected(EventV3IO rejected) {
        this.rejected = rejected;
    }

    public EventV3IO getAnswer() {
        return answer;
    }

    public void setAnswer(EventV3IO answer) {
        this.answer = answer;
    }

    public Map<String, String> getOpts() {
        return opts;
    }

    public void setOpts(Map<String, String> opts) {
        this.opts = opts;
    }
}
