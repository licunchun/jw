import re
import sqlite3


if __name__ == '__main__':
    connection_courses = sqlite3.connect('database.db')
    cursor_courses = connection_courses.cursor()

    connection_teachers = sqlite3.connect('database.db')
    cursor_teachers = connection_teachers.cursor()
    dct = {}

    cursor_courses.execute('drop table courses')
    cursor_courses.execute('create table if not exists courses ('
                           'code        text    primary key     not null,'
                           'name        text    not null,'
                           'period      text    not null,'
                           'credits     text    not null,'
                           'time        text,'
                           'stdCount    text    not null,'
                           'limitCount  text    not null,'
                           'courseType  text,'
                           'department  text,'
                           'campus      text,'
                           'examMode    text,'
                           'Language    text,'
                           'education   text,'
                           'classType   text,'
                           'teachers    text)')

    cursor_teachers.execute('drop table teachers')
    cursor_teachers.execute('create table if not exists teachers ('
                            'name    text    primary key     not null,'
                            'account text    not null,'
                            'key     text    not null,'
                            'classes text)')

    with open('page.html', 'r', encoding='utf-8') as file:
        text = file.read()

    result = eval(text)
    for i in range(len(result)):
        del result[i]['id'], result[i]['periodsPerWeek'], result[i]['dateTimePlaceText'], \
            result[i]['graduateAndPostgraduate'], result[i]['courseGradation'], result[i]['courseCategory'], \
            result[i]['courseClassify']
        result[i]['dateTimePlacePersonText'] = result[i]['dateTimePlacePersonText']['cn']
        result[i]['course'] = result[i]['course']['cn']
        result[i]['courseType'] = result[i]['courseType']['cn']
        result[i]['openDepartment'] = result[i]['openDepartment']['cn']
        result[i]['campus'] = result[i]['campus']['cn']
        result[i]['examMode'] = result[i]['examMode']['cn']
        result[i]['teachLang'] = result[i]['teachLang']['cn']
        result[i]['education'] = result[i]['education']['cn']
        result[i]['classType'] = result[i]['classType']['cn']
        for j in range(len(result[i]['teacherAssignmentList'])):
            result[i]['teacherAssignmentList'][j] = result[i]['teacherAssignmentList'][j]['cn']

        result[i]['stdCount'] = '0'
        try:
            cursor_courses.execute('insert into courses (code, name, period, credits, time, stdCount, limitCount, '
                                   'courseType, department, campus, examMode, Language, education, classType, '
                                   'teachers) '
                                   'values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)', (
                                    result[i]['code'], result[i]['course'], str(result[i]['period']), str(result[i]['credits']),
                                    result[i]['dateTimePlacePersonText'], str(result[i]['stdCount']),
                                    str(result[i]['limitCount']), result[i]['courseType'], result[i]["openDepartment"],
                                    result[i]['campus'], result[i]['examMode'], result[i]['teachLang'],
                                    result[i]['education'], result[i]['classType'], str(result[i]['teacherAssignmentList']).strip('[]').replace("'", "")))
            connection_courses.commit()
        except sqlite3.IntegrityError:
            pass

        for j in result[i]['teacherAssignmentList']:
            if j in dct:
                dct[j] += ', ' + result[i]['code']
            else:
                dct[j] = result[i]['code']
    cursor_courses.close()
    connection_courses.close()

    i = 0
    for key, value in dct.items():
        try:
            cursor_teachers.execute('insert into teachers (name, account, key, classes) '
                                    'values (?, ?, ?, ?)', (key, '0'*(5-len(str(i)))+str(i), '00000000', value))
            i += 1
        except sqlite3.IntegrityError:
            pass
    connection_teachers.commit()
    cursor_teachers.close()
    connection_teachers.close()
