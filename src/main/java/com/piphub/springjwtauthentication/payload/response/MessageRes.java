package com.piphub.springjwtauthentication.payload.response;

import lombok.Data;

@Data
public class MessageRes {
    private String message;
    private String messageKh;
    private String messageCh;
    private String code;
    private Object data;

    public MessageRes() {
    }

    public MessageRes(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public MessageRes(Object data) {
        this.code = "200";
        this.message = "Success";
        this.messageKh = "ជោគជ័យ";
        this.messageCh = "成功";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessageSuccess(Object data) {
        this.code = "200";
        this.message = "Success";
        this.messageKh = "ជោគជ័យ";
        this.messageCh = "成功";
        this.data = data;
    }

    public void setOpenAccountSuccess() {
        this.code = "200";
        this.message = "User Open Account successfully!";
        this.messageKh = "អ្នកប្រើប្រាស់បើកគណនីដោយជោគជ័យ!";
        this.messageCh = "User Open Account successfully!";
    }

    public void setInviteCodeSuccess(String data) {
        this.code = "200";
        this.message = "User Check Invite code successfully!";
        this.messageKh = "អ្នកប្រើប្រាស់ពិនិត្យលេខកូដអញ្ជើញដោយជោគជ័យ!";
        this.messageCh = "User Check Invite code successfully!";
        this.data = data;
    }

    public void setMessageCreateSuccess(Object data) {
        this.code = "200";
        this.message = "Create Success";
        this.messageCh = "Create Success";
        this.messageKh = "បង្កើតជោគជ័យ";
        this.data = data;
    }

    public void setMessageUpdateSuccess(Object data) {
        this.code = "200";
        this.message = "Update Success";
        this.messageCh = "Update Success";
        this.messageKh = "ធ្វើបច្ចុប្បន្នភាពជោគជ័យ";
        this.data = data;
    }

    public void setMessageDeleteSuccess(Object data) {
        this.code = "200";
        this.message = "Delete Success";
        this.messageCh = "Delete Success";
        this.messageKh = "ធលុបជោគជ័យ";
        this.data = data;
    }

    public void badRequest(Object data) {
        this.code = "400";
        this.message = "Bad Request";
        this.messageCh = "Bad Request";
        this.messageKh = "សំណើមិនល្អ";
        this.data = data;
    }

    public void internalServerError(Object data) {
        this.code = "500";
        this.message = "Internal Server Error";
        this.messageCh = "Internal Server Error";
        this.messageKh = "កំហុសម៉ាស៊ីនមេខាងក្នុង";
        this.data = data;
    }

    public void setNameAlreadyUse() {
        this.code = "400";
        this.message = "The name is already use";
        this.messageCh = "The name is already use";
        this.messageKh = "ឈ្មោះនេះប្រើរួចហើយ";
    }

    public void setDataNotFound() {
        this.code = "400";
        this.message = "Get Data Not Found";
        this.messageCh = "Get Data Not Found";
        this.messageKh = "ឈ្រកមិនឃើញទិន្នន័យ";
    }

    public void setPhoneAlreadyUse() {
        this.code = "400";
        this.message = "The phone number is already used";
        this.messageCh = "The name is already used";
        this.messageKh = "លេខ\u200Bទូរសព្ទ\u200Bត្រូវ\u200Bបាន\u200Bប្រើ\u200Bរួច\u200Bហើយ";
    }

    public void setEmailAlreadyUse() {
        this.code = "400";
        this.message = "The email is already used";
        this.messageCh = "The email is already used";
        this.messageKh = "អ៊ីមែលត្រូវបានប្រើប្រាស់រួចហើយ";
    }

    public void setNationalAlreadyUse() {
        this.code = "400";
        this.message = "The National ID is already used";
        this.messageCh = "The National ID is already used";
        this.messageKh = "អត្តសញ្ញាណប័ណ្ណជាតិត្រូវបានប្រើប្រាស់រួចហើយ";
    }

    public void setConfirmPasswordNotMatch() {
        this.code = "400";
        this.message = "Confirm password does not match!";
        this.messageCh = "Confirm password does not match!";
        this.messageKh = "បញ្ជាក់ពាក្យសម្ងាត់មិនត្រូវគ្នា";
    }
    public void setOldPasswordNotMatch() {
        this.code = "400";
        this.message = "Your password does not match!";
        this.messageCh = "Your password does not match!";
        this.messageKh = "ពាក្យសម្ងាត់របស់អ្នកមិនត្រូវគ្នា";
    }


    public void setInvalidStage() {
        this.code = "400";
        this.message = "Invalid Step Open Account";
        this.messageCh = "Invalid Step Open Account";
        this.messageKh = "ជំហានបើកគណនីមិនត្រឹមត្រូវ";
    }

    public void setConfirmOTPNotMatch() {
        this.code = "400";
        this.message = "Confirm OTP does not match!";
        this.messageCh = "Confirm OTP does not match!";
        this.messageKh = "បញ្ជាក់ OTP មិនត្រូវគ្នា!";
    }

    public void getUserNotFound() {
        this.code = "400";
        this.message = "The user is not found!";
        this.messageCh = "The user is not found!";
        this.messageKh = "រកមិនឃើញអ្នកប្រើប្រាស់ទេ!";
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MessageRes{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
