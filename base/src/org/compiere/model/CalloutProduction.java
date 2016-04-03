/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.util.Properties;

import org.compiere.util.Env;

/**
 *	Production Callouts
 *	
 *  @author Jorg Janke
 *  @version $Id: CalloutProduction.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 *  
 *  @contrib Paul Bowden (adaxa)  set locator from product
 *  
 *  @author mckayERP www.mckayERP.com
 *  		<li> #286 Provide methods to treat ASI fields in a consistent manner.
 */
public class CalloutProduction extends CalloutEngine
{
	/**
	 *  Product modified
	 * 		Set Attribute Set Instance
	 *
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer M_Product_ID = (Integer)value;
		if (M_Product_ID == null)
			M_Product_ID = Integer.valueOf(0);
		
		//	Set Attribute
		MProduct product = MProduct.get(ctx, M_Product_ID);
		setAndTestASI(ctx, WindowNo, Env.isSOTrx(ctx, WindowNo), mTab, 
				"M_AttributeSetInstance_ID", product, null);
		
		if ( product != null )
		{
			if ( product.getM_Locator_ID() > 0)
				mTab.setValue("M_Locator_ID", product.getM_Locator_ID());
		}
		
		return "";
	}   //  product
}	//	CalloutProduction
