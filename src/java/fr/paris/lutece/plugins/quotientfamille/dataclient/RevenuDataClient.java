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
package fr.paris.lutece.plugins.quotientfamille.dataclient;

import fr.paris.lutece.plugins.quotientfamille.service.RedirectUtils;
import fr.paris.lutece.plugins.quotientfamille.web.FranceConnectSampleApp;
import fr.paris.lutece.plugins.franceconnect.oidc.Token;
import fr.paris.lutece.plugins.franceconnect.oidc.dataclient.AbstractDataClient;
import fr.paris.lutece.plugins.franceconnect.service.MapperService;
import fr.paris.lutece.portal.service.util.AppLogService;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * RevenuDataClient
 */
public class RevenuDataClient extends AbstractDataClient
{

    public static final String ATTRIBUTE_QUOTIENTFAMILIAL = "quotientfamille-dc-userrevenu";

    @Override
    public void handleToken( Token token, HttpServletRequest request, HttpServletResponse response )
    {
        try
        {
            String data = getData( token );
            if ( StringUtils.isNotEmpty( data ) ) {
                UserRevenu userRevenu = MapperService.parse( data, UserRevenu.class );
                request.getSession( true ).setAttribute(ATTRIBUTE_QUOTIENTFAMILIAL, userRevenu.getQuotientfamilial() );
            }
            String strRedirectUrl = RedirectUtils.getViewUrl( request, FranceConnectSampleApp.VIEW_DEMARCHE_ETAPE2 );
            response.sendRedirect( strRedirectUrl );
        }
        catch ( IOException ex )
        {
            AppLogService.error( "Error DataClient Revenu : " + ex.getMessage(  ), ex );
        }
    }
}
