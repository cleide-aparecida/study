import unittest

from testing.Pages import LoginPage
from testing.Support import Base


class meu_primeiro_test(unittest.TestCase):
    def test_meu_primeiro_test(self):
        navegador = Base.iniciar_navegador()
        LoginPage(navegador). \
            fazer_login("julio0001", "123456"). \
            clicar_me().clicar_aba_more_about_you(). \
            clicar_botao_add_more_data_about_you(). \
            escolher_tipo_contato("Phone"). \
            digitar_contato("+553599979956"). \
            clicar_salvar()
        Base.fechar_navegador(navegador)


if __name__ == '__main__':
    unittest.main()
