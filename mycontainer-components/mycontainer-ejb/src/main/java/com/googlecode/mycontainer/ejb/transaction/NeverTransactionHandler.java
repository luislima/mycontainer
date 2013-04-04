/*
 * Copyright 2008 Whohoo Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable
 * law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.googlecode.mycontainer.ejb.transaction;

import javax.ejb.EJBException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;

import com.googlecode.mycontainer.kernel.reflect.proxy.ProxyChain;
import com.googlecode.mycontainer.kernel.reflect.proxy.Request;


public class NeverTransactionHandler extends AbstractTransactionHandler {

	private static final long serialVersionUID = -8896717448166839192L;

	public Object intercept(Request request, ProxyChain chain) throws Throwable {
		TransactionManager tm = getTransactionManager();
		Transaction tx = tm.getTransaction();

		if (tx != null) {
			throw new EJBException("Transaction found");
		}

		return chain.proceed(request);
	}
}