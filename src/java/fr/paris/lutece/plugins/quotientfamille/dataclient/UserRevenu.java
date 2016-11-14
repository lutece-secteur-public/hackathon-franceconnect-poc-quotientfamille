/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

/*
Sample from dgfip
 * {"rfr":0,"nbPart":1.0}
 */

package fr.paris.lutece.plugins.quotientfamille.dataclient;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.paris.lutece.plugins.quotientfamille.business.QuotientFamilial;


/**
 * UserRevenu
 */
public class UserRevenu implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private QuotientFamilial _qf = new QuotientFamilial();

    /**
     * Sets the Records
     * @param records The Records
     */
    @JsonProperty( "nbPart" )
    public void setNbPart( double nbPart )
    {
        _qf.setNombreParts( nbPart );
    }

    /**
     * Sets the Records
     * @param records The Records
     */
    @JsonProperty( "rfr" )
    public void setRfr( int rfr )
    {
        _qf.setRevenuFiscalReference( rfr );
    }

    /**
     * Returns the RFR
     * @return The RFR
     */
    public QuotientFamilial getQuotientfamilial(  )
    {
        return _qf;
    }
}
