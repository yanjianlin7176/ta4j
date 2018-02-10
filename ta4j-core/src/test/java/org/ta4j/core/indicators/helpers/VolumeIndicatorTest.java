/*
  The MIT License (MIT)

  Copyright (c) 2014-2017 Marc de Verdelhan, Ta4j Organization & respective authors (see AUTHORS)

  Permission is hereby granted, free of charge, to any person obtaining a copy of
  this software and associated documentation files (the "Software"), to deal in
  the Software without restriction, including without limitation the rights to
  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
  the Software, and to permit persons to whom the Software is furnished to do so,
  subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators.helpers;

import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.Indicator;
import org.ta4j.core.TestUtils;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.AbstractIndicatorTest;
import org.ta4j.core.mocks.MockBar;
import org.ta4j.core.mocks.MockTimeSeries;
import org.ta4j.core.num.Num;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static junit.framework.TestCase.assertEquals;

public class VolumeIndicatorTest extends AbstractIndicatorTest<Indicator<Num>, Num> {

    public VolumeIndicatorTest(Function<Number, Num> numFunction) {
        super(numFunction);
    }

    @Test
    public void indicatorShouldRetrieveBarVolume() {
        TimeSeries series = new MockTimeSeries(numFunction);
        VolumeIndicator volumeIndicator = new VolumeIndicator(series);
        for (int i = 0; i < 10; i++) {
            assertEquals(volumeIndicator.getValue(i), series.getBar(i).getVolume());
        }
    }

    @Test
    public void sumOfVolume() {
        List<Bar> bars = new ArrayList<Bar>();
        bars.add(new MockBar(0, 10,numFunction));
        bars.add(new MockBar(0, 11,numFunction));
        bars.add(new MockBar(0, 12,numFunction));
        bars.add(new MockBar(0, 13,numFunction));
        bars.add(new MockBar(0, 150,numFunction));
        bars.add(new MockBar(0, 155,numFunction));
        bars.add(new MockBar(0, 160,numFunction));
        VolumeIndicator volumeIndicator = new VolumeIndicator(new MockTimeSeries(bars), 3);

        TestUtils.assertNumEquals(volumeIndicator.getValue(0), 10);
        TestUtils.assertNumEquals(volumeIndicator.getValue(1), 21);
        TestUtils.assertNumEquals(volumeIndicator.getValue(2), 33);
        TestUtils.assertNumEquals(volumeIndicator.getValue(3), 36);
        TestUtils.assertNumEquals(volumeIndicator.getValue(4), 175);
        TestUtils.assertNumEquals(volumeIndicator.getValue(5), 318);
        TestUtils.assertNumEquals(volumeIndicator.getValue(6), 465);
    }
}
