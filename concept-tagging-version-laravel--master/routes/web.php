<?php

use Illuminate\Foundation\Application;
use Illuminate\Support\Facades\Route;
use Inertia\Inertia;



Route::get('/', function () {
    return Inertia::render('Welcome', [
        'canLogin' => Route::has('login'),
        'canRegister' => Route::has('register'),
        'laravelVersion' => Application::VERSION,
        'phpVersion' => PHP_VERSION,
    ]);
});

Route::group(['auth:sanctum', 'verified'], function() {

    Route::post('/create/card', 'App\Http\Controllers\ContentController@store_card');
    Route::post('/create/serie','App\Http\Controllers\ContentController@store_serie');


    Route::get('/leaderboard','App\Http\Controllers\LeaderBoardController@index')->name('leaderboard');  //Permet d'afficher la page de leaderboard
    Route::get('/create','App\Http\Controllers\ContentController@index')->name('create');
    Route::get('/game/play/{id}','App\Http\Controllers\GameController@index')->name('game_play');
    Route::post('/game/update','App\Http\Controllers\GameController@update_score')->name('game_play_update');



    Route::get('/dashboard','App\Http\Controllers\DashboardController@index')->name('dashboard');



    Route::get('/manage/{id}','App\Http\Controllers\ManageController@index')->name('manage');

    Route::get('/manage/delete/serie/{id}','App\Http\Controllers\ManageController@delete_serie')->name('manage_delete_serie');
    Route::get('/manage/delete/card/{id}','App\Http\Controllers\ManageController@delete_card')->name('manage_delete_card');


});

