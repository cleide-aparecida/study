from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.support.ui import Select

class MePage(object):

    def __init__(self, navegador):
        self._navegador = navegador

    def clicar_aba_more_about_you(self):
        self._navegador.find_element_by_link_text("MORE DATA ABOUT YOU").click()
        return self

    def clicar_botao_add_more_data_about_you(self):
        self._navegador.find_element_by_xpath("//button[@data-target=\"addmoredata\"]").click()
        return AddContactPage(self._navegador)

    def get_toast(self):
        return self._navegador.find_element_by_id("toast-container")

class AddContactPage(object):
    def __init__(self, navegador):
        self._navegador = navegador

    def escolher_tipo_contato(self, type):
        campo_type = self._navegador.find_element_by_id("addmoredata").find_element_by_name("type")
        Select(campo_type).select_by_visible_text(type)
        return self

    def digitar_contato(self, contact):
        self._navegador.find_element_by_id("addmoredata").find_element_by_name("contact").send_keys(contact)
        return self

    def clicar_salvar(self):
        self._navegador.find_element_by_id("addmoredata").find_element_by_link_text("SAVE").click()
        return MePage(self._navegador)

class SecretaPage(object):
    def __init__(self, navegador):
        self._navegador = navegador

    def clicar_me(self):
        self._navegador.find_element_by_class_name("me").click()
        return MePage(self._navegador)

class LoginPage(object):

    def __init__(self, navegador):
        self._navegador = navegador


    def fazer_login(self, user, password):
        self._navegador.find_element_by_link_text("Sign in").click()
        self._navegador.find_element_by_id("signinbox").find_element_by_name("login").send_keys(user)
        self._navegador.find_element_by_id("signinbox").find_element_by_name("password").send_keys(password)
        self._navegador.find_element_by_id("signinbox").find_element_by_link_text("SIGN IN").click()
        return SecretaPage(self._navegador)

class Mensagem(object):

    def __init__(self, navegador):
        self._navegador = navegador


