package com.sistemahoteles.springboot.reservas.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.sistemahoteles.springboot.reservas.models.entity.Usuario;
import com.sistemahoteles.springboot.reservas.models.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
   
	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("info_adicional", "Informacion: ".concat(authentication.getName()));
		
		info.put("username", usuario.getUsername());
		info.put("rol", usuario.getRoles());
		info.put("id", usuario.getId());
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
