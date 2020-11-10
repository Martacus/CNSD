import json
import psycopg2


def lambda_handler(event, context):

    conn = psycopg2.connect(
        host="database-lambdales.cio1a4ux4r0h.us-east-1.rds.amazonaws.com",
        database="postgres",
        user="postgres",
        password="Welkom123")

    cur = conn.cursor()

    print('PostgreSQL database notitie:')
    cur.execute('SELECT * FROM notitie')

    db_version = cur.fetchone()
    print(db_version)

    cur.close()

    # TODO implement
    return {
        'statusCode': 200,
        'body': {
            'id': db_version[0],
            'tekst': db_version[1],
            'aangemaakt': db_version[2].__str__(),
            'laatst_bewerkt': db_version[3].__str__()
        }
    }
