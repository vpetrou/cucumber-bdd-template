package com.vpetrou.domain;


public enum ContractEndpointEnum {
    //App Gateway API
    PATH_SEARCH_DECLARATIONS("/v1/declarations/search"),
    PATH_COUNT_DECLARATIONS("/v1/declarations/count"),
    PATH_GET_DECLARATION("/v1/declarations/.*/dossier"),
    PATH_CREATE_DECLARATION("/v1/declarations/new"),
    PATH_DECLARATION_VERSIONS_DELTA("/v1/declarations/.*/versions-delta"),
    PATH_DECLARATION_STATUS_HISTORY("/v1/declarations/.*/status-history"),
    PATH_DECLARATION_TRADER_NOTIFICATIONS("/v1/customs-data/.*/notifications"),
    PATH_DECLARATION_GET_TRADER_NOTIFICATION("/v1/customs-data/notifications/.*"),
    PATH_DECLARATION_NOTES("/v1/customs-data/.*/notes"),
    PATH_CREATE_NOTE("/v1/customs-data/notes"),
    PATH_DECLARATION_CONTROL_RESULTS("/v1/customs-data/.*/control-tasks/.*/control-results"),
    PATH_DECLARATION_COVERAGE_STATUS("/v1/customs-data/.*/coverage/status"),
    PATH_DECLARATION_COVERAGE_STATUS_ID("/v1/customs-data/.*/coverage/status/.*"),
    PATH_DECLARATION_CUSTOMS_DEBT("/v1/customs-data/.*/customs-debt"),
    PATH_DECLARATION_CUSTOMS_DEBT_ID("/v1/customs-data/.*/customs-debt/.*"),
    PATH_DECLARATION_COMPLETE_CUSTOMS_DEBT_WORK_TASK("/v1/customs-data/complete-work-task/.*/"),
    PATH_DECLARATION_GET_FINAL_CUSTOMS_POSITION("/v1/customs-data/.*/customs-positions/current"),
    PATH_DECLARATION_GET_PREVIOUS_CUSTOMS_POSITION("/v1/customs-data/.*/customs-positions/previous"),
    PATH_DECLARATION_GET_CUSTOMS_POSITION("/v1/customs-data/.*/customs-positions/"),
    PATH_DECLARATION_RISK_ASSESSMENT_RESULTS("/v1/customs-data/.*/risk-assessment-results"),
    PATH_DECLARATION_RISK_ASSESSMENT_RESULT("/v1/customs-data/.*/risk-assessment-results/.*"),
    PATH_DECLARATION_VALIDATION_RESULTS("/v1/customs-data/.*/validation-results"),
    PATH_DECLARATION_VALIDATION_RESULT("/v1/customs-data/.*/validation-results/.*"),
    PATH_VALIDATE_ADDITIONAL_MESSAGE("/v1/additional-messages/validate"),
    PATH_SUBMIT_ADDITIONAL_MESSAGE("/v1/additional-messages/new"),
    PATH_DECLARATION_GET_ADDITIONAL_MESSAGES("/v1/additional-messages/declaration/.*"),
    PATH_SEARCH_EXTERNAL_MESSAGES("/v1/external-messages/search"),
    PATH_COUNT_EXTERNAL_MESSAGES("/v1/external-messages/count"),
    PATH_DECLARATION_GET_EXTERNAL_MESSAGES("/v1/external-messages/declaration/.*"),
    PATH_SEARCH_WORK_TASKS("/v1/work-tasks/search"),
    PATH_COUNT_WORK_TASKS("/v1/work-tasks/count"),
    PATH_TRANSFER_WORK_TASK("/v1/work-tasks/.*/transfer/.*"),
    PATH_RELEASE_WORK_TASK("/v1/work-tasks/.*/release"),
    PATH_CLAIM_WORK_TASK("/v1/work-tasks/.*/claim/.*"),
    PATH_EXTERNAL_PARTIES("/v1/parties/external"),
    PATH_INTERNAL_PARTIES("/v1/parties/internal"),
    PATH_DECLARATION_CONTROL_WORK_TASKS("/v1/control-work-tasks/.*"),
    PATH_GET_CONTROL_WORK_TASK("/v1/control-work-tasks/control-work-task/.*"),
    PATH_REGISTER_CONTROL_RESULT("/v1/control-work-tasks/register-result/.*"),
    PATH_REQUEST_ADDITIONAL_DOCUMENT("/v1/control-work-tasks/request-additional-document/.*"),
    PATH_DELETE_ADDITIONAL_DOCUMENT("/v1/control-work-tasks/delete-additional-document/.*/.*"),
    PATH_REGISTER_ADDITIONAL_DOCUMENT("/v1/control-work-tasks/update-document-presentation-data/.*"),
    PATH_REGISTER_CUSTOMS_POSITION("/v1/customs-data/register-position/.*"),
    PATH_REGISTER_COVERAGE("/v1/customs-data/register-coverage/.*"),
    PATH_REGISTER_ACT_ON_EXPIRED_DEADLINE("/v1/control-work-tasks/complete-expired-deadline/.*"),
    PATH_GET_ADDITIONAL_MESSAGE("/v1/additional-messages/additional-message/.*"),
    PATH_REGISTER_CUSTOMS_POSITION_ON_MESSAGE("/v1/additional-messages/complete-customs-position-on-message/.*"),
    PATH_GET_ADDITIONAL_MESSAGE_BY_WORK_TASK_ID("/v1/additional-messages/additional-message/work-task/.*"),
    PATH_GET_FILTER_BUSINESS_RULE_TEMPLATES("/v1/business-rules/templates"),
    PATH_GET_BUSINESS_RULE_GROUPS("/v1/business-rules/ruleGroups"),
    PATH_RULE_INSTANCES("/instances/"),

    //Reference Data API
    PATH_GET_CODELIST_ITEMS("/referencedata/api/codelistItems"),
    PATH_GET_CODELIST_ITEM("/referencedata/api/codelistItem"),
    PATH_VERIFY_CODELIST_ITEM("/referencedata/api/verifyCodelistItem");

    ContractEndpointEnum(String path) {
        this.path = path;
    }

    private String path;


    public String getPath() {
        return path;
    }

    public static ContractEndpointEnum get(String path) {
        for (ContractEndpointEnum contractEndpointEnum : values()) {
            if (contractEndpointEnum.path.equals(path)) {
                return contractEndpointEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return path;
    }
}
