<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Inertia\Inertia;

class LeaderBoardController extends Controller
{
    /**
     * Demande d'affichage de la page de leaderboard
     */
    public function index()
    {

        $user_score = auth()->user()['user_score'];

        return Inertia::render('LeaderBoard/LeaderBoard', [
            'user_score' => $user_score,
            'all_user' => User::orderBy('user_score','desc')->get()
        ]);
    }
}
