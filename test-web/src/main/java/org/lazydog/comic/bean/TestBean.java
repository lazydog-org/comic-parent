package org.lazydog.comic.bean;

import java.util.Date;


/**
 *
 * @author R4R
 */
public class TestBean {

    private Date endMonthYear;
    private Date monthYear;
    private Date startMonthYear;

    public Date getEndMonthYear() {
        return this.endMonthYear;
    }

    public Date getMonthYear() {
        return this.monthYear;
    }

    public Date getStartMonthYear() {
        return this.startMonthYear;
    }

    public void setEndMonthYear(Date endMonthYear) {
        this.endMonthYear = endMonthYear;
    }

    public void setMonthYear(Date monthYear) {
        this.monthYear = monthYear;
    }

    public void setStartMonthYear(Date startMonthYear) {
        this.startMonthYear = startMonthYear;
    }
}
