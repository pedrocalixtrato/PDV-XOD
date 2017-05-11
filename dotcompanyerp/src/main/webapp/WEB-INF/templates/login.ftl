
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9;chrome=1">
<title>Dotcompany</title>
<style type="text/css">
html, body {
	height: 100%;
	margin: 0;
}
.error
        {
        	font-family: 'Open Sans', Arial, sans-serif;
			font-style: normal;
			font-variant: normal;
            font-weight: normal;
            color: #C30;
        }
</style>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	$(document).ready(function() {
		if($("#loginError").length){
			$("#loginCaptcha").css("display", "block");
		}
	  }
	  );
</script>
<link rel="stylesheet" type="text/css" href="../VAADIN/themes/dotcompany/styles.css">
</head>


<body scroll="auto" class=" v-generated-body v-sa v-ch v-webkit v-win" cz-shortcut-listen="true">
<div id="dotcompany-1047860588" class=" v-app dotcompany">
  <div tabindex="1" class="v-ui login v-scrollable" style="height: 100%; width: 100%;">
    <div class="v-loading-indicator" style="position: absolute; display: none;"></div>
    
    <!--Inicio Quadro Branco -->
    <div class="v-csslayout v-layout v-widget root v-csslayout-root v-has-width v-has-height" style="position: absolute; height: 100%; width: 100%;">
      <div class="v-label v-widget login-bg v-label-login-bg v-label-undef-w" style="display: inline-block;"></div>
      <div class="v-verticallayout v-layout v-vertical v-widget login-layout v-verticallayout-login-layout v-has-width v-has-height" style="height: 100%; width: 100%;">
        <div class="v-expand" style="padding-top: 0px;">
          <div class="v-slot v-slot-login-panel v-align-center v-align-middle" style="height: 100%; margin-top: 0px;">
            <div class="v-csslayout v-layout v-widget login-panel v-csslayout-login-panel" style=""> 
              
              <!--Início Bem vindo e nome empresa-->
              <div class="v-horizontallayout v-layout v-horizontal v-widget labels v-horizontallayout-labels v-has-width v-margin-top v-margin-right v-margin-bottom v-margin-left" style="width: 100%; height: 44px;">
                <div class="v-expand" style="padding-left: 0px;">
                  <div class="v-slot v-slot-h4 v-align-middle" style="width: 50%; margin-left: 0px;">
                    <div class="v-label v-widget h4 v-label-h4 v-label-undef-w" style="display: inline-block;">Seja Bem Vindo(a) ao DotCompany ERP</div>
                  </div>
                  <div class="v-slot v-slot-h2 v-slot-light v-align-right v-align-middle" style="width: 50%;">
                    <div class="v-label v-widget h2 v-label-h2 light v-label-light v-label-undef-w" style="display: inline-block;">(Versão alpha 1.0.0.1)</div>
                  </div>
                </div>
              </div>
              <!--Fim Bem vindo e nome empresa--> 
              
              <!--Início Formulários-->
              <form method="post" action="${rc.getContextPath()}/processLogin">
                <div class="v-verticallayout v-layout v-vertical v-widget fields v-verticallayout-fields v-margin-top v-margin-right v-margin-bottom v-margin-left" style="">
                  <div class="v-slot v-align-center">
                    <div class="v-widget v-has-caption v-caption-on-top" style="">
                      <div class="v-caption" style="">
                      	<span class="v-captiontext">Usuário</span>
	                    <span> 
	                      <input type="text" name="username" class="v-textfield v-widget v-textfield-focus" style="height:27px" tabindex="0" autofocus="autofocus">
	                    </span>
    	              </div>
                     
                      <div class="v-caption" style="">
                      	<span class="v-captiontext">Senha</span>
                      	<span style="padding-left:9px">
	                      	<input type="password" name="password" class="v-textfield v-widget" style="height:27px" tabindex="0">
                      	</span>
	                   </div>
	                   
	                    <div id="loginCaptcha" class="v-caption" style="display:none">
	                    	<span>
	                    		<img src="jcaptcha.jpg" /> 
	                    	</span>
	                    	<span>
		                    	<input type="text" id="jcaptcha" name="jcaptcha" class="v-textfield v-widget" style="height:27px; width:80px;" tabindex="0" />
    						</span>
    					</div>
                    </div>
                  </div>
                  <div class="v-spacing"></div>
                  <div class="v-slot v-slot-default v-align-bottom v-align-center">
                    <input type="submit" value="Entrar" class=" v-button-wrap v-button v-widget default v-button-default v-button-caption" style="height:27px">
                  </div>
                </div>
              </form>
              <!--Fim Formulários-->
              	  <div class="v-slot v-slot-default v-align-bottom">
                    <a href = "${rc.getContextPath()}/esqueciMinhaSenha" >Esqueceu a senha?</a>
                  </div>
              <#if isError?? && isError?string == "true">
                <div id="loginError" class="error">Usuário e/ou senha incorretos. Verifique os dados e tente novamente</div>
              </#if> 
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--Fim Quadro Branco--> 
    
  </div>
</div>
</body>
</html>



            

