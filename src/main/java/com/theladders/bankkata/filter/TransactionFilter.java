package com.theladders.bankkata.filter;

import com.theladders.bankkata.transaction.Transaction;

public class TransactionFilter {
	private DateRange dateFilter;
	private TypeFilter typeFilter;

	public TransactionFilter(DateRange dateFilter, TypeFilter typeFilter) {
		this.dateFilter = dateFilter;
		this.typeFilter = typeFilter;

	}

	public boolean meetsCriteria(Transaction transaction) {
		boolean datePass = transaction.withinRange(dateFilter);
		boolean typePass = typeFilter.meetsCriteria(transaction);
		return datePass && typePass;

	}

}
