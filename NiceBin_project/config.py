# all of the config run on app.py  在这里运行不同的设置加载在app,**P10 9 有不同
class BaseConfig(object):
    DEBUG = False
    SECRET_KEY = 'jkhv2346%@elkrjugl$^234'
    SQLALCHEMY_DATABASE_URI = 'sqlite:///posts.db'

class TestConfig(BaseConfig):
    DEBUG = True
    TESTING = True
    WTF_CSRF_ENABLED = False
    SQLALCHEMY_DATABASE_URI = 'sqlite:///:memory:'

class DevelopmentConfig(BaseConfig):
    DEBUG = True


