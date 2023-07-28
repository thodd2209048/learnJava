package manageEmployee;

public class RemoveAccent {

    public static String removeAccent(String s) {
        s = s.replaceAll("[áàảãạăắằẳẵặâấầẩẫậ]", "a");
        s = s.replaceAll("[ÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬ]", "A");
        s = s.replaceAll("[éèẻẽẹêếềểễệ]", "e");
        s = s.replaceAll("[ÉÈẺẼẸÊẾỀỂỄỆ]", "E");
        s = s.replaceAll("[óòỏõọôốồổỗộơớờởỡợ]", "o");
        s = s.replaceAll("[ÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢ]", "O");
        s = s.replaceAll("[íìỉĩị]", "i");
        s = s.replaceAll("[ÍÌỈĨỊ]", "I");
        s = s.replaceAll("[úùủũụưứừửữự]", "u");
        s = s.replaceAll("[ÚÙỦŨỤƯỨỪỬỮỰ]", "U");
        s = s.replaceAll("[ýỳỷỹỵ]", "y");
        s = s.replaceAll("[ÝỲỶỸỴ]", "Y");
        s = s.replaceAll("đ", "d");
        s = s.replaceAll("Đ", "D");
        return s;
    }
}
