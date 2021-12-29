# Generated by Django 4.0 on 2021-12-29 12:10

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('workouts', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='ExerciseSet',
            fields=[
                ('sets', models.IntegerField()),
                ('reps', models.IntegerField()),
                ('exercise', models.OneToOneField(on_delete=django.db.models.deletion.DO_NOTHING, primary_key=True, serialize=False, to='workouts.exercise')),
            ],
        ),
    ]