from selenium import webdriver
from webdriver_manager.chrome import ChromeDriverManager

class Base(object):

    @staticmethod
    def iniciar_navegador():
        navegador = webdriver.Chrome(ChromeDriverManager().install())
        navegador.maximize_window()
        navegador.get("http://www.juliodelima.com.br/taskit")
        navegador.implicitly_wait(10)
        return navegador

    @staticmethod
    def fechar_navegador(navegador):
        navegador.quit()