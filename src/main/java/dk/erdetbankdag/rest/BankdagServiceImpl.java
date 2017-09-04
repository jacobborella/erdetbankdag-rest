/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package dk.erdetbankdag.rest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import dk.erdetbankdag.util.Holiday;


class Response {
	private boolean erDetBankDag;

	public boolean isErDetBankDag() {
		return erDetBankDag;
	}

	public void setErDetBankDag(boolean erDetBankDag) {
		this.erDetBankDag = erDetBankDag;
	}
	
}

class MonthResponse {
	private Response[] bankingCalendar;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month + 1;
	}
	public void setMonth(int month) {
		this.month = month - 1;
	}
	public Response[] getBankingCalendar() {
		return bankingCalendar;
	}
	public void setBankingCalendar(Response[] bankingCalendar) {
		this.bankingCalendar = bankingCalendar;
	}
	private int year, month;
	protected void init(int month, int year) {
		this.year = year;
		this.month = month;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		bankingCalendar = new Response[daysInMonth];
		for(int i=0; i < bankingCalendar.length; i++) {
			bankingCalendar[i] = new Response();
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		for(Response res: bankingCalendar) {
			res.setErDetBankDag(!Holiday.isItHoliday(calendar));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
}

@Path("/")
@Service
public class BankdagServiceImpl {

    @GET
    @Path("/month")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody MonthResponse month() {
        MonthResponse res = new MonthResponse();
        Calendar theDate = getDate();
        res.init(theDate.get(Calendar.MONTH), theDate.get(Calendar.YEAR));
        return res;
    }

    @GET
    @Path("/day")
    @Produces(MediaType.APPLICATION_JSON)
    public @ResponseBody Response day() {
        Response res = new Response();
        res.setErDetBankDag(!Holiday.isItHoliday(getDate()));
        return res;
    }
    
    private Calendar getDate() {
        Calendar theDate = GregorianCalendar.getInstance();
        theDate.setTimeZone(TimeZone.getTimeZone("Europe/Copenhagen"));
        return theDate;
    }
}
