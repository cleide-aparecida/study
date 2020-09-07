from behave import *
from testing.Support import Base
from testing.Pages import LoginPage, Mensagem


@given(u'O usuario abre o navegador')
def iniciar_navegador(context):
    context.navegador = Base.iniciar_navegador()
    context.login_page = LoginPage(context.navegador)


@given(u'Faz login')
def step_impl(context):
    context.click_me = context.login_page.fazer_login("julio0001", "123456").clicar_me()


@when(u'Clica na aba More About You')
def step_impl(context):
    context.aba_more = context.click_me.clicar_aba_more_about_you()


@when(u'Clica no botao Add More Data About You')
def step_impl(context):
    context.botao_more = context.aba_more.clicar_botao_add_more_data_about_you()


@when(u'Escolhe o tipo de contato')
def step_impl(context):
    context.type_contact = context.botao_more.escolher_tipo_contato("Phone")


@when(u'Digita o contato escolhido')
def step_impl(context):
    context.digitar_contact = context.type_contact.digitar_contato("+553599979956")


@when(u'Clica em salvar')
def step_impl(context):
    context.salvar = context.digitar_contact.clicar_salvar()


@then(u'uma mensagem deve ser exibida')
def step_impl(context):
    assert "Your contact has been added!" == context.salvar.get_toast().text
