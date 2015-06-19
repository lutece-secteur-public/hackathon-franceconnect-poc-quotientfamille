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
package fr.paris.lutece.plugins.quotientfamille.service;

import fr.paris.lutece.portal.service.util.AppPropertiesService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierre
 */
public class TranchesService 
{
    private static final String PROPERTY_TRANCHES = "quotientfamille.tranches";
    private static Integer[] _tranches;

    
    public static int getTranche( int nQuotient )
    {
        if( _tranches == null )
        {
            _tranches = getTranches();
            
        }
        int nFloor = 0;
        int nCeiling = _tranches[0];
        int nTranche = 1;
        
        for( int i = 0 ; i < _tranches.length - 1 ; i++ )
        {
            nTranche = i + 1;
            if( nQuotient >= nFloor && nQuotient < nCeiling )
            {
                return nTranche;
            }
            nFloor = _tranches[i];
            nCeiling = _tranches[i+1]; 
        }
        return nTranche;
    }        

    private static Integer[] getTranches() 
    {
        String strTranches = AppPropertiesService.getProperty( PROPERTY_TRANCHES );
        String[] stranches =  strTranches.split( "," );
        List<Integer> list = new ArrayList<Integer>();
        for( String strTranche : stranches )
        {
            list.add( Integer.parseInt(strTranche.trim()));
        }
        return (Integer[]) list.toArray();
    }
    
}
