package org.example.crm.vo;

public class ReviseDomainVO {
    private boolean success;
    private Object domain;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getDomain() {
        return domain;
    }

    public void setDomain(Object domain) {
        this.domain = domain;
    }
}
