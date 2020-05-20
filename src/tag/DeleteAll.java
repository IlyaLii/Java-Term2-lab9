package tag;

import entity.Ad;
import entity.AdList;
import entity.User;
import helper.AdListHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Array;
import java.util.HashSet;

public class DeleteAll extends SimpleTagSupport {

    public void doTag() throws JspException, IOException {
        String errorMessage = null;
        AdList adList = (AdList) getJspContext().getAttribute("ads", PageContext.APPLICATION_SCOPE);
        User currentUser = (User) getJspContext().getAttribute("authUser", PageContext.SESSION_SCOPE);
        if (currentUser==null) {
            errorMessage = "Вы пытаетесь изменить сообщение, к которому не обладаете правами доступа!";
        }
        if (errorMessage==null) {

            HashSet<Ad> list = adList.getAds();
            for(Ad ad: list) {
                if (ad.getAuthorId() == currentUser.getId()) {
                    adList.deleteAd(ad);
                }
            }

            AdListHelper.saveAdList(adList);
        }
        getJspContext().setAttribute("errorMessage", errorMessage, PageContext.SESSION_SCOPE);
    }
}
