<?php

namespace App\Http\Controllers;

use App\Models\CardSerie;
use App\Models\Card;

use Illuminate\Http\Request;
use Inertia\Inertia;

class DashboardController extends Controller
{
    public function index()
    {

        $all_series = CardSerie::orderBy('serie_name')->get();

        return Inertia::render('Dashboard', [
            'all_series' => $all_series
        ]);

    }
}
